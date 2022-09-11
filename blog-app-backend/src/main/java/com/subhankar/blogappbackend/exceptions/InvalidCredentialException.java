package com.subhankar.blogappbackend.exceptions;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message) {
        super(message);
    }

    public InvalidCredentialException() {
        super();
    }
}
