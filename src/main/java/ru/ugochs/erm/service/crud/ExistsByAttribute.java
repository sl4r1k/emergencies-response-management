package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;

public class ExistsByAttribute<T extends AbstractEntity> extends CrudOperation<T, Boolean> {
    protected final String attribute;
    protected final Object value;
    protected final Class<T> type;

    public ExistsByAttribute(String attribute, Object value, Db db, Class<T> type) {
        super(db);
        this.attribute = attribute;
        this.value = value;
        this.type = type;
    }

    @Override
    protected Boolean perform() {
        return this.db.createQuery(
            String.format(
                "SELECT CASE WHEN COUNT(x) > 0 THEN TRUE ELSE FALSE END FROM %s AS x WHERE x.%s = :value",
                this.type.getSimpleName(),
                this.attribute
            ),
            Boolean.class
        ).setParameter("value", this.value)
            .getSingleResult();
    }
}
