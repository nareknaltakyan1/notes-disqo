CREATE TABLE USER
(
    ID                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    EMAIL                  NVARCHAR(255) UNIQUE NOT NULL,
    PASSWORD               NVARCHAR(255)        NOT NULL,
    CREATION_DATE          DATETIME             NOT NULL,
    ROLE                   INT                  NOT NULL,
    LAST_MODIFICATION_DATE DATETIME             NOT NULL,
    INDEX (EMAIL),

    CONSTRAINT fk_USER_ROLE FOREIGN KEY (ROLE)
        REFERENCES notes.RF_ROLE (ID)
)
    engine = InnoDB;
