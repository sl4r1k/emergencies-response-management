package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;

public class ExistsByAttributeIgnoringItself<T extends AbstractEntity> extends ExistsByAttribute<T> {
    private final Long id;

    public ExistsByAttributeIgnoringItself(String attribute, Object value, Long id, Db db, Class<T> type) {
        super(attribute, value, db, type);
        this.id = id;
    }

    @Override
    protected Boolean perform() {
        return this.db.createQuery(
            String.format(
                "SELECT CASE WHEN COUNT(x) > 0 THEN TRUE ELSE FALSE END FROM %s AS x WHERE x.%s = :value AND x.id <> :id",
                this.type.getSimpleName(),
                this.attribute,
                this.id
            ),
            Boolean.class
        ).setParameter("value", this.value)
            .setParameter("id", this.id)
            .getSingleResult();
    }
}
