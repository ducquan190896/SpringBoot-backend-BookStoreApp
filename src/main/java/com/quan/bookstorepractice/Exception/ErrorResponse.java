package com.quan.bookstorepractice.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private Throwable throwable;
    private LocalDateTime localDateTime;
    public ErrorResponse() {
    }

    public ErrorResponse(String message, Throwable throwable, LocalDateTime localDateTime) {
        this.message = message;
        this.throwable = throwable;
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "ErrorResponse [message=" + message + ", throwable=" + throwable + ", localDateTime=" + localDateTime
                + "]";
    }
    
    
}
