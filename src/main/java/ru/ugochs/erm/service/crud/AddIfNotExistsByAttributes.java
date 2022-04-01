package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.Parameters;
import javax.persistence.EntityExistsException;

public class AddIfNotExistsByAttributes<T extends AbstractEntity> extends Add<T> {
    private final Parameters parameters;
    private final String message;

    public AddIfNotExistsByAttributes(
        T entity,
        Parameters parameters,
        String message,
        Db db
    ) {
        super(entity, db);
        this.parameters = parameters;
        this.message = message;
    }

    @Override
    public T perform() {
        if (
            new ExistsByAttributes<>(
                this.entity,
                this.parameters,
                this.db
            ).perform()
        ) {
            throw new EntityExistsException(this.message);
        }
        return super.perform();
    }
}
