package com.notes.disqo.web.rest;

import com.notes.disqo.dto.SingleFieldWrapper;
import com.notes.disqo.service.FileFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/file")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    private final FileFacade fileFacade;

    public FileController(FileFacade fileFacade) {
        this.fileFacade = fileFacade;
    }

    @PostMapping(path = "/upload/note/{id}", consumes = "multipart/form-data")
    public ResponseEntity<SingleFieldWrapper<String>> uploadFile(
            @RequestParam MultipartFile file,
            @PathVariable(value = "id", required = false) final Long id
    ) {
        log.debug("REST request to upload file : {}", file.getOriginalFilename());
        return ResponseEntity.ok(SingleFieldWrapper.of(fileFacade.storeFile(file, id)));
    }

    @GetMapping(path = "/download/{guid}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String guid, HttpServletRequest request) {
        Resource resource = fileFacade.loadFile(guid);

        String contentType = getContentType(resource, request);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private String getContentType(Resource resource, HttpServletRequest request) {
        String contentType;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getPath());
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }
        return contentType;
    }

    @GetMapping(path = "/pre-sign-url")
    public ResponseEntity<SingleFieldWrapper<URL>> getPreSignUrl(@RequestParam String key) throws IOException {
        log.debug("REST request to get file pre sign url : {}", key);
        return ResponseEntity.ok(SingleFieldWrapper.of(fileFacade.generatePreSingURL(key)));
    }
}
