package ru.ugochs.erm.view.util;

import java.time.LocalDate;

public class StringAsLocalDate {
    private final String string;

    public StringAsLocalDate(String string) {
        this.string = string;
    }

    public LocalDate value() {
        return this.string == null
            ? null
            : LocalDate.parse(this.string);
    }
}
