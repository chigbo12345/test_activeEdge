package com.project.activeedge.exceptions;

public class CustomCheckedException extends Exception {
    public CustomCheckedException(String message) {
        super(message);
    }

    public CustomCheckedException(Throwable cause){
        super(cause);
    }

    public CustomCheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
