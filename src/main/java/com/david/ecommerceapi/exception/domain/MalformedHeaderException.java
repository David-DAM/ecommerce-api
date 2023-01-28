package com.david.ecommerceapi.exception.domain;

public class MalformedHeaderException extends BadRequestException{

    private static final String DESCRIPTION = "Token with wrong format";

    public MalformedHeaderException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
