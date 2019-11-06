package com.andrada.mountaineering.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No username introduced")
public class NoUserNameException extends Exception {
    public NoUserNameException(String message) {
        super(message);
    }
    private static final long serialVersionUID = -3332292346834265371L;

    public NoUserNameException() {
        super("no username introduced");
    }
}
