package ru.ugochs.erm.service;

import ru.ugochs.erm.entity.Index;
import ru.ugochs.erm.service.crud.*;
import java.util.List;

public class FilterIndexesByRelativeName {
    private final GetAll<Index> operation;

    public FilterIndexesByRelativeName(String name, Db db) {
        this.operation = name.isBlank()
            ? new GetAllIndexesByLevel1(db)
            : new GetAllIndexesByRelativeName(name, db);
    }

    public List<Index> perform() {
        return this.operation.perform();
    }
}
