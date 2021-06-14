package com.notes.disqo.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UnknownFileExtensionException extends RuntimeException {

    public UnknownFileExtensionException() {
        super("Unknown File Extension");
    }
}