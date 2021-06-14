package com.notes.disqo.service.aws;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.notes.disqo.config.CloudStorageConfig;
import com.notes.disqo.dto.FileMetadataDTO;
import com.notes.disqo.exaption.UnknownFileExtensionException;
import com.notes.disqo.util.FileUtil;
import com.notes.disqo.validator.FileValidator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("AwsStorageService")
public class AwsStorageService implements StorageService {

    private static final String PATH_UPLOADS = "uploads";
    private static final String PATH_VIDEO = "/video";
    private static final String PATH_DOCUMENTS = "/documents";
    private static final String PATH_PHOTO = "/photo";

    private final AwsGateway awsGateway;
    private final CloudStorageConfig cloudStorageConfig;
    private final FileValidator fileValidator;
    private final ResourceLoader resourceLoader;

    public AwsStorageService(
            AwsGateway awsGateway,
            CloudStorageConfig cloudStorageConfig,
            FileValidator fileValidator,
            ResourceLoader resourceLoader
    ) {
        this.awsGateway = awsGateway;
        this.cloudStorageConfig = cloudStorageConfig;
        this.fileValidator = fileValidator;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public FileMetadataDTO store(MultipartFile file, String guid) {
        String extension = FileUtil.getFileExtension(file);
        if (!StringUtils.hasText(extension)) {
            throw new UnknownFileExtensionException();
        }
        String filename = FileUtil.getFilenameWithExtension(guid, extension);
        String contentType = FileUtil.getFileContentType(file);

        if (!fileValidator.validateFileType(contentType, extension)) {
            throw new UnknownFileExtensionException();
        }

        String key = PATH_UPLOADS + "/" + filename;
        Map<String, String> metadata = new HashMap<>();
        metadata.put("originalName", file.getName());
        try {
            PutObjectResult result = awsGateway.uploadFile(
                    cloudStorageConfig.getStorage().getBucketName(),
                    key,
                    FileUtil.getFileContentType(file),
                    file.getInputStream(),
                    metadata
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileMetadataDTO fileMetadata = new FileMetadataDTO();
        fileMetadata.setOriginalName(file.getOriginalFilename());
        fileMetadata.setGuid(filename);
        fileMetadata.setType(contentType);
        fileMetadata.setPath(awsGateway.getResourceUrl(cloudStorageConfig.getStorage().getBucketName(), key).toString());
        return fileMetadata;
    }

    @Override
    public void removeFile(String file) {
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return resourceLoader.getResource(filename);
    }

    public URL generatePreSignUrl(String objectKey) {
        String finalObjectKey = objectKey;
        String type = Optional
                .ofNullable(objectKey)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(finalObjectKey.lastIndexOf(".") + 1))
                .orElse(null);

        objectKey = PATH_UPLOADS + objectKey;

        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60;
        expiration.setTime(expTimeMillis);
        return awsGateway.generatePreSingUrl(cloudStorageConfig.getStorage().getBucketName(), objectKey, expiration, HttpMethod.GET);
    }
}
