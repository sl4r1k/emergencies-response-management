package ru.ugochs.erm.view.util;

import ru.ugochs.erm.exception.ApplicationException;
import java.util.Objects;

public class UnwrappedErrorMessage {
    private Throwable unwrapped;

    public UnwrappedErrorMessage(Throwable throwable) {
        this.unwrapped = throwable;
    }

    public String text() {
        if (this.unwrapped instanceof ApplicationException) {
            return this.unwrapped.getMessage();
        }
        if (Objects.isNull(this.unwrapped.getCause())) {
            return "Что-то пошло не так";
        }
        this.unwrapped = this.unwrapped.getCause();
        return this.text();
    }
}
