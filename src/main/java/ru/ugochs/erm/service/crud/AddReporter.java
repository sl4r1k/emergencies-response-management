package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Reporter;

public class AddReporter extends AddIfNotExists<Reporter> {
    public AddReporter(Reporter reporter, Db db) {
        super(
            reporter,
            "name",
            reporter.getName(),
            "Заявитель с таким названием уже существует",
            db
        );
    }
}
