# A user in the system. It contains the following fields:
# - Email: Non-blank, valid email address, unique
# - Password: Non-blank, at least 8 characters
# - Create Time
# - Last Update Time

CREATE TABLE USER
(
    ID                      BIGINT          AUTO_INCREMENT  PRIMARY KEY,
    EMAIL                   NVARCHAR(255)   UNIQUE          NOT NULL,
    PASSWORD                NVARCHAR(255)                   NOT NULL,
    CREATION_DATE           DATETIME                        NOT NULL,
    LAST_MODIFICATION_DATE  DATETIME                        NOT NULL,
    INDEX (EMAIL)
)
    engine = InnoDB;
