package com.david.ecommerceapi.exception.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorMessage {
    private String exception;
    private String message;
    private String path;

    public ErrorMessage(Exception exception, String path){
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
    }


}