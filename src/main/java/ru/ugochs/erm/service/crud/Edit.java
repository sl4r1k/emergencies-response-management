package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;

public class Edit<T extends AbstractEntity> extends CrudOperation<T, T> {
    protected final T entity;

    public Edit(T entity, Db db) {
        super(db);
        this.entity = entity;
    }

    @Override
    public T perform() {
        return this.db.execute(status -> this.db.merge(this.entity));
    }
}
