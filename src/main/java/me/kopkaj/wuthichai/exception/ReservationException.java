package me.kopkaj.wuthichai.exception;

public class ReservationException extends ApplicationException {
    public ReservationException(String message) {
        super(message);
    }

    public ReservationException(String message, Throwable cause) {
        super(message, cause);
    }
}
