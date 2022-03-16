package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;

public abstract class CrudOperation<T extends AbstractEntity, R> {
    protected final Db db;

    protected CrudOperation(Db db) {
        this.db = db;
    }

    protected abstract R perform();
}
