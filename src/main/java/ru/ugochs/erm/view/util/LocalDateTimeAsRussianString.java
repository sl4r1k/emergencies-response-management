package ru.ugochs.erm.view.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalDateTimeAsRussianString {
    private static final String RUSSIAN_FORMAT = "HH:mm dd.MM.yyyy";
    private final LocalDateTime dateTime;

    public LocalDateTimeAsRussianString(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return Optional.ofNullable(this.dateTime)
            .map(date -> this.dateTime.format(
                DateTimeFormatter.ofPattern(
                    LocalDateTimeAsRussianString.RUSSIAN_FORMAT
                )
            )).orElse(null);
    }
}
