package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;

public class AddOrIgnoreIfNotExists<T extends AbstractEntity> extends Add<T> {
    private final String attribute;
    private final Object value;

    public AddOrIgnoreIfNotExists(
        T entity,
        String attribute,
        Object value,
        Db db
    ) {
        super(entity, db);
        this.attribute = attribute;
        this.value = value;
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
            return null;
        }
        return super.perform();
    }
}
