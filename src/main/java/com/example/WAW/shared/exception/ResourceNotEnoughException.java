package com.example.WAW.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotEnoughException extends RuntimeException{

    public ResourceNotEnoughException(){
        super();
    }

    public ResourceNotEnoughException(String message){
        super(message);
    }

}
