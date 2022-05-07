package ru.ugochs.erm.view.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalDateAsRussianString {
    private static final String RUSSIAN_FORMAT = "dd.MM.yyyy";
    private final LocalDate date;

    public LocalDateAsRussianString(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return Optional.ofNullable(this.date)
            .map(date -> this.date.format(
                DateTimeFormatter.ofPattern(
                    LocalDateAsRussianString.RUSSIAN_FORMAT
                )
            )).orElse(null);
    }
}
