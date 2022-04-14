package ru.ugochs.erm.exception;

public class ApplicationIllegalArgumentException extends ApplicationException {
    public ApplicationIllegalArgumentException() {}

    public ApplicationIllegalArgumentException(String message) {
        super(message);
    }

    public ApplicationIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationIllegalArgumentException(Throwable cause) {
        super(cause);
    }

    protected ApplicationIllegalArgumentException(
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
