package com.notes.disqo.service;

import com.notes.disqo.domain.FileMetadata;
import com.notes.disqo.repository.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileMetadataService {

    private final FileMetadataRepository fileMetadataRepository;

    private final NoteService noteService;

    @Autowired
    public FileMetadataService(FileMetadataRepository fileMetadataRepository, NoteService noteService) {
        this.fileMetadataRepository = fileMetadataRepository;
        this.noteService = noteService;
    }

    @Transactional
    public void saveFile(String originalName, String guid, String type, String path, Long id) {
        FileMetadata file = new FileMetadata();
        file.setOriginalName(originalName);
        file.setGuid(guid);
        file.setType(type);
        file.setPath(path);
        file.setNote(noteService.getNoteById(id));

        fileMetadataRepository.save(file);

    }

    public FileMetadata getFile(String guid) {
        return fileMetadataRepository.findByGuid(guid);
    }

    public boolean isGuidExists(String guid) {
        return fileMetadataRepository.existsByGuid(guid);
    }
}
