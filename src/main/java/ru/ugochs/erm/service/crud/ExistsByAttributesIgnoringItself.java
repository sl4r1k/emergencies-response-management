package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.Parameters;
import ru.ugochs.erm.service.ParametrizedQuery;

public class ExistsByAttributesIgnoringItself<T extends AbstractEntity> extends CrudOperation<T, Boolean> {
    private final T entity;
    private final Parameters parameters;
    private final Long id;

    public ExistsByAttributesIgnoringItself(T entity, Parameters parameters, Long id, Db db) {
        super(db);
        this.entity = entity;
        this.parameters = parameters;
        this.id = id;
    }

    @Override
    protected Boolean perform() {
        return new ParametrizedQuery<>(
            this.db.createQuery(
                String.format(
                    "SELECT CASE WHEN COUNT(x) > 0 THEN TRUE ELSE FALSE END FROM %s AS x WHERE %s AND x.id <> :id",
                    this.entity.getClass().getSimpleName(),
                    this.parameters.asString(),
                    this.id
                ),
                Boolean.class
            ),
            this.parameters
        ).query()
            .setParameter("id", this.id)
            .getSingleResult();
    }
}
