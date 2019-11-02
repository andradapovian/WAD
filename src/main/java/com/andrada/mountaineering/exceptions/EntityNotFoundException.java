package com.andrada.mountaineering.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Entity Not Found")
public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(String message) {
        super(message);
    }

    private static final long serialVersionUID = -3332292346834265371L;

    public EntityNotFoundException(){
        super("EntityNotFoundException");
    }
}
