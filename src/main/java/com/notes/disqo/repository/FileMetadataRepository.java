package com.notes.disqo.repository;

import com.notes.disqo.domain.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {
    FileMetadata findByGuid(String guid);

    boolean existsByGuid(String guid);
}