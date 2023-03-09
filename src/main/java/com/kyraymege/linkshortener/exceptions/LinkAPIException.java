package com.kyraymege.linkshortener.exceptions;

import org.springframework.http.HttpStatus;

public class LinkAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public LinkAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public LinkAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }


    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
