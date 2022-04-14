package ru.ugochs.erm.exception;

public class EntityExistsException extends ApplicationException {
    public EntityExistsException() {
    }

    public EntityExistsException(String message) {
        super(message);
    }

    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExistsException(Throwable cause) {
        super(cause);
    }

    protected EntityExistsException(
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
