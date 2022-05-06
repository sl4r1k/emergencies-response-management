package ru.ugochs.erm.view.util;

import java.time.LocalTime;

public class StringAsLocalTime {
    private final String string;

    public StringAsLocalTime(String string) {
        this.string = string;
    }

    public LocalTime value() {
        return this.string == null
            ? null
            : LocalTime.parse(this.string);
    }
}
