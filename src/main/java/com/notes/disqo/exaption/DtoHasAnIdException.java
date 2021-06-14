package com.notes.disqo.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DtoHasAnIdException extends RuntimeException {

    public DtoHasAnIdException() {
        super("DTO Has An Id");
    }
}
