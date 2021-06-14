package com.notes.disqo.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserIsNotFoundException extends RuntimeException {

    public UserIsNotFoundException() {
        super("User Is Not Found");
    }
}
