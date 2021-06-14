package com.notes.disqo.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String guid) {
        super("File Not Found: " + guid);
    }
}
