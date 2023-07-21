package me.kopkaj.wuthichai.exception;

/**
 * A base exception use by this Wuthichai application
 */
public class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
