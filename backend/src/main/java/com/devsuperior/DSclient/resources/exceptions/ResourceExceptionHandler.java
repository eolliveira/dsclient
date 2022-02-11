package com.devsuperior.DSclient.resources.exceptions;

import com.devsuperior.DSclient.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<StandartError> entity(ResourceNotFoundException e, Http){
        StandartError standartError = new StandartError();
        standartError.setTimestamp(Instant.now());
        standartError.set
    }



}
