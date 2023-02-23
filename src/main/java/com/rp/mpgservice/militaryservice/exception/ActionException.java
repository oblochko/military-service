package com.rp.mpgservice.militaryservice.exception;

import com.rp.mpgservice.militaryservice.entity.Action;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ActionException extends Exception{

    public ActionException(String msg) {
        super(msg);
    }
}
