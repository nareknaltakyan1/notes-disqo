CREATE TABLE FILE_METADATA
(
    ID                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    original_name          NVARCHAR(255) NOT NULL,
    guid                   NVARCHAR(255) NOT NULL,
    type                   NVARCHAR(255) NOT NULL,
    path                   NVARCHAR(255) NOT NULL,
    CREATION_DATE          DATETIME      NOT NULL,
    LAST_MODIFICATION_DATE DATETIME      NOT NULL,
    NOTE_ID                BIGINT        NOT NULL,

    CONSTRAINT fk_NOTE_FILE FOREIGN KEY (NOTE_ID)
        REFERENCES notes.NOTE (ID)
)
    engine = InnoDB;
