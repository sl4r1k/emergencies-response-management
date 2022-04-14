package ru.ugochs.erm.exception;

public class IllegalArgumentException extends ApplicationException {
    public IllegalArgumentException() {}

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentException(Throwable cause) {
        super(cause);
    }

    protected IllegalArgumentException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(
            message,
            cause,
            enableSuppression,
            writableStackTrace
        );
    }
}
