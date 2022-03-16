package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;

public class Remove<T extends AbstractEntity> extends CrudOperation<T, Void> {
    protected final T entity;

    public Remove(T entity, Db db) {
        super(db);
        this.entity = entity;
    }

    @Override
    public Void perform() {
        return this.db.execute(status -> {
            if (this.db.contains(this.entity)) {
                this.db.remove(this.entity);
            } else {
                this.db.remove(this.db.merge(this.entity));
            }
            return null;
        });
    }
}
