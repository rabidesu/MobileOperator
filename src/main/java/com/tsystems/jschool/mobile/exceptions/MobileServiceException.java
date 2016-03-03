package com.tsystems.jschool.mobile.exceptions;

/**
 * Created by Alexandra on 03.03.2016.
 */
public class MobileServiceException extends Exception {

    public MobileServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileServiceException(Throwable cause) {
        super(cause);
    }
}
