package com.kyraymege.linkshortener.models;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamps;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timestamps, String message, String details) {
        this.timestamps = timestamps;
        this.message = message;
        this.details = details;
    }


    public LocalDateTime getTimestamps() {
        return timestamps;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}