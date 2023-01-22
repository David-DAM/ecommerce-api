package com.example.ecommerceapi.exception.domain;

public class BadRequestException extends RuntimeException{

    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
