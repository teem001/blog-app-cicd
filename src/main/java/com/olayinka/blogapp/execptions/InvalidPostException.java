package com.olayinka.blogapp.execptions;

public class InvalidPostException extends RuntimeException{
    public InvalidPostException(String message) {
        super(message);
    }

    public InvalidPostException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPostException(Throwable cause) {
        super(cause);
    }


}
