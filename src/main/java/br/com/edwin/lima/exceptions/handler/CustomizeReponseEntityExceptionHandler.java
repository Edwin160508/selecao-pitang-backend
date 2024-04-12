package br.com.edwin.lima.exceptions.handler;

import br.com.edwin.lima.exceptions.ExceptionResponse;
import br.com.edwin.lima.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import java.lang.Exception;
import java.util.*;
@ControllerAdvice
@RestController
public class CustomizeReponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse>handlerAllExceptions(Exception ex, WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse>handleResourceNotFoundExceptions(Exception ex, WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


}
