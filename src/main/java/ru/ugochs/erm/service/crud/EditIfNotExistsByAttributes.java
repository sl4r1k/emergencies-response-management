package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.Parameters;
import javax.persistence.EntityExistsException;

public class EditIfNotExistsByAttributes<T extends AbstractEntity> extends Edit<T> {
    private final Parameters parameters;
    private final Long id;
    private final String message;

    public EditIfNotExistsByAttributes(
        T entity,
        Parameters parameters,
        Long id,
        String message,
        Db db
    ) {
        super(entity, db);
        this.parameters = parameters;
        this.id = id;
        this.message = message;
    }

    @Override
    public T perform() {
        if (
            new ExistsByAttributesIgnoringItself<>(
                this.entity,
                this.parameters,
                this.id,
                this.db
            ).perform()
        ) {
            throw new EntityExistsException(this.message);
        }
        return super.perform();
    }
}
