package com.david.ecommerceapi.exception.domain;

public class InvalidTokenException extends BadRequestException{

    private static final String DESCRIPTION = "Token expired";

    public InvalidTokenException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
