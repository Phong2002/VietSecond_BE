package com.phenikaa.vietsecond.Utils.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
    public class HandleException {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception e, WebRequest request) {
        return new ErrorMessage(400,e.getMessage());
    }

        @ExceptionHandler(BindException.class)
        @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
        public ErrorMessage handleAllBindException(BindException e, WebRequest request) {
            return new ErrorMessage(400, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }

}
