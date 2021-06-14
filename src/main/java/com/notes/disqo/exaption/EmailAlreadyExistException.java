package com.notes.disqo.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException() {
        super("Email Already Exist");
    }
}
