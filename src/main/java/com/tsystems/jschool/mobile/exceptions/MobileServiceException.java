package com.tsystems.jschool.mobile.exceptions;


public class MobileServiceException extends RuntimeException {

    public MobileServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileServiceException(Throwable cause) {
        super(cause);
    }
    public MobileServiceException(String message) {
        super(message);
    }
}
