/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.roosevelt.seniorproject.nflpickem;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author mruth
 */
@ControllerAdvice
public class MyExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);
    
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ValidationErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.warn("We have an exception: MANV");
        //let's process
        ValidationErrorMessage vem = new ValidationErrorMessage();
        
        BindingResult br = e.getBindingResult();
        
        
        for (FieldError fe : br.getFieldErrors()) {
            FieldErrorMessage fem = new FieldErrorMessage();
            fem.setField(fe.getField());
            fem.setMessage(fe.getDefaultMessage());
            vem.addFieldErrorMessage(fem);
        }
        
        
        
        return new ResponseEntity(vem,HttpStatus.BAD_REQUEST);
        

    }
    
    
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        logger.warn("We have an exception");
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    
}
