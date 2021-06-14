CREATE TABLE NOTE
(
    ID                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE                  NVARCHAR(50)   NOT NULL,
    CONTENT                NVARCHAR(1000) NOT NULL,
    CREATION_DATE          DATETIME       NOT NULL,
    LAST_MODIFICATION_DATE DATETIME       NOT NULL,
    USER_ID                BIGINT         NOT NULL,
    INDEX (TITLE),

    CONSTRAINT fk_USER_NOTE FOREIGN KEY (USER_ID)
        REFERENCES notes.USER (ID)
)
    engine = InnoDB;

