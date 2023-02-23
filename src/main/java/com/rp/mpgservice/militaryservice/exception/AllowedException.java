package com.rp.mpgservice.militaryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AllowedException extends Exception{
    public AllowedException(String msg) {
        super(msg);
    }
}
