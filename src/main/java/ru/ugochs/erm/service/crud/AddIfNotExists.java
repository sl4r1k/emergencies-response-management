package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import javax.persistence.EntityExistsException;

public class AddIfNotExists<T extends AbstractEntity> extends Add<T> {
    private final String attribute;
    private final Object value;
    private final String message;

    public AddIfNotExists(T entity, String attribute, Object value, String message, Db db) {
        super(entity, db);
        this.attribute = attribute;
        this.value = value;
        this.message = message;
    }

    @Override
    public T perform() {
        if (
            new ExistsByAttribute<>(
                this.attribute,
                this.value,
                this.db,
                this.entity.getClass()
            ).perform()
        ) {
            throw new EntityExistsException(this.message);
        }
        return super.perform();
    }
}
