package com.notes.disqo.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsersAreNotTheSameException extends RuntimeException {

    public UsersAreNotTheSameException() {
        super("Users Are Not The Same");
    }
}
