package com.notes.disqo.exaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NoteIsNotExistException extends RuntimeException {

    public NoteIsNotExistException() {
        super("Note Is Not Exist");
    }
}
