package com.example.ecommerceapi.exception.domain;

public class NotFoundException extends RuntimeException{

    private static final String DESCRIPTION = "Not found Exception ";

    public NotFoundException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
