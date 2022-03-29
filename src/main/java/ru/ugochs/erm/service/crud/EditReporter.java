package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Reporter;

public class EditReporter extends EditIfNotExists<Reporter> {
    public EditReporter(Reporter reporter, Db db) {
        super(
            reporter,
            "name",
            reporter.getName(),
            reporter.getId(),
            "Заявитель с таким названием уже существует",
            db
        );
    }
}
