package com.bizlog.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(Long clientId){
        super("configuration already exists with cliend id : "+clientId);
    }
}
