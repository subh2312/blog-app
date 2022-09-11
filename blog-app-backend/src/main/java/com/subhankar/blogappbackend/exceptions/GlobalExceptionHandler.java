package com.subhankar.blogappbackend.exceptions;

import com.subhankar.blogappbackend.payloads.ApiResponse;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        String message = e.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err)->{
            String fieldName = ((FieldError)err).getField();
            String errorMessage = err.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(InvalidCredentialException e){
        String message = e.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(apiResponse,HttpStatus.UNAUTHORIZED);
    }
}
