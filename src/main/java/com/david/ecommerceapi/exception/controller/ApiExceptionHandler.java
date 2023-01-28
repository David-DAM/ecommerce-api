package com.david.ecommerceapi.exception.controller;

import com.david.ecommerceapi.exception.domain.BadRequestException;
import com.david.ecommerceapi.exception.domain.ErrorMessage;
import com.david.ecommerceapi.exception.domain.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import  org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            MissingRequestHeaderException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }

//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler({ForbidenException.class})
//    @ResponseBody
//    public ErrorMessage forbiddenRequest(HttpServletRequest request, Exception exception){
//        return new ErrorMessage(exception, request.getRequestURI());
//    }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler({ConflictException.class})
//    @ResponseBody
//    public ErrorMessage conflict(HttpServletRequest request, Exception exception){
//        return new ErrorMessage(exception, request.getRequestURI());
//    }

}
