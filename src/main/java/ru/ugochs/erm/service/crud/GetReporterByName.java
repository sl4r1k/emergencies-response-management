package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Reporter;

public class GetReporterByName extends GetSingle<Reporter> {
    public GetReporterByName(String name, Db db) {
        super("name", name, db, Reporter.class);
    }
}
