package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;

public class GetById<T extends AbstractEntity> extends CrudOperation<T, T> {
    protected final Long id;
    protected final Class<T> type;

    public GetById(Long id, Db db, Class<T> type) {
        super(db);
        this.id = id;
        this.type = type;
    }

    @Override
    public T perform() {
        return this.db.find(this.type, this.id);
    }
}
