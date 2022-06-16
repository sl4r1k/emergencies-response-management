package ru.ugochs.erm.view.util;

import java.time.LocalDate;
import java.util.Optional;

public class Period {
    private final LocalDate from;
    private final LocalDate to;

    public Period(LocalDate from,LocalDate to) {
        this.from = from;
        this.to = Optional.ofNullable(to)
            .orElse(LocalDate.now());
    }

    public String text() {
        if (this.from.isEqual(this.to)) {
            return String.format(
                "за %s",
                new LocalDateAsRussianString(
                    this.from
                )
            );
        }
        return String.format(
            "за период %s-%s",
            new LocalDateAsRussianString(this.from),
            new LocalDateAsRussianString(this.to)
        );
    }
}
