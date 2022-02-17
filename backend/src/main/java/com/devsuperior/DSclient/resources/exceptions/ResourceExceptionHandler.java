package com.devsuperior.DSclient.resources.exceptions;

import com.devsuperior.DSclient.services.exceptions.DatabaseException;
import com.devsuperior.DSclient.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<StandartError> entity(ResourceNotFoundException e, HttpServletRequest request){
        int httpStatus = HttpStatus.NOT_FOUND.value();
        StandartError standartError = new StandartError();
        standartError.setTimestamp(Instant.now());
        standartError.setError("Resource not found");
        standartError.setMessage(e.getMessage());
        standartError.setPath(request.getRequestURI());
        standartError.setStatus(httpStatus);
        return  ResponseEntity.status(httpStatus).body(standartError);
    }

    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<StandartError> database(DatabaseException e, HttpServletRequest request){
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        StandartError standartError = new StandartError();
        standartError.setTimestamp(Instant.now());
        standartError.setError("Database exception");
        standartError.setMessage(e.getMessage());
        standartError.setPath(request.getRequestURI());
        standartError.setStatus(httpStatus);
        return  ResponseEntity.status(httpStatus).body(standartError);
    }
}
