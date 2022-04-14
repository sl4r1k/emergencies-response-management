package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.entity.Index;
import ru.ugochs.erm.exception.EntityExistsException;

public class EditIfNotExists<T extends AbstractEntity> extends Edit<T> {
    private final String attribute;
    private final Object value;
    private final Long id;
    private final String message;

    public EditIfNotExists(
        T entity,
        String attribute,
        Object value,
        Long id,
        String message,
        Db db
    ) {
        super(entity, db);
        this.attribute = attribute;
        this.value = value;
        this.id = id;
        this.message = message;
    }

    @Override
    public T perform() {
        if (
            new ExistsByAttributeIgnoringItself<>(
                this.attribute,
                this.value,
                this.id,
                this.db,
                Index.class
            ).perform()
        ) {
            throw new EntityExistsException(this.message);
        }
        return super.perform();
    }
}
