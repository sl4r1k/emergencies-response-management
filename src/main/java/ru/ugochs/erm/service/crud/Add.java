package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;

public class Add<T extends AbstractEntity> extends CrudOperation<T, T> {
    protected final T entity;

    public Add(T entity, Db db) {
        super(db);
        this.entity = entity;
    }

    @Override
    public T perform() {
        return this.db.execute(act -> {
            this.db.persist(this.entity);
            return this.entity;
        });
    }
}
