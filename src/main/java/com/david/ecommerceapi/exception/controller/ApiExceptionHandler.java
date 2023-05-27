package com.david.ecommerceapi.exception.controller;

import com.david.ecommerceapi.exception.domain.BadRequestException;
import com.david.ecommerceapi.exception.domain.ErrorMessage;
import com.david.ecommerceapi.exception.domain.InvalidTokenException;
import com.david.ecommerceapi.exception.domain.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
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

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class,
            TypeNotPresentException.class,
            EntityNotFoundException.class,
    })
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest request, Exception exception){
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            InvalidTokenException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class,
            MissingRequestHeaderException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            //MethodArgumentNotValidException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception){//, MethodArgumentNotValidException ex
       //Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach((error) -> {
//            errors.put(error.getField(), error.getDefaultMessage());
//        });

        //Handle business exceptions in other methods for the specific cases
        return new ErrorMessage(exception, request.getRequestURI());//, errors
    }

//    public Map<String,String> businnesException(BusinessException e){
//        Map<String,String> errors= new HashMap<>();
//        errors.put("message","exeption");
//
//        return errors;
//    }

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
