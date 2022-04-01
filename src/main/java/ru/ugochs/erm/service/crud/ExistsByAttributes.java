package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.Parameters;
import ru.ugochs.erm.service.ParametrizedQuery;

public class ExistsByAttributes<T extends AbstractEntity> extends CrudOperation<T, Boolean> {
    private final T entity;
    private final Parameters parameters;
    
    public ExistsByAttributes(T entity, Parameters parameters, Db db) {
        super(db);
        this.entity = entity;
        this.parameters = parameters;
    }

    @Override
    protected Boolean perform() {
        return new ParametrizedQuery<>(
            this.db.createQuery(
                String.format(
                    "SELECT CASE WHEN COUNT(x) > 0 THEN TRUE ELSE FALSE END FROM %s AS x WHERE "
                        + this.parameters.asString(),
                    this.entity.getClass().getSimpleName()
                ),
                Boolean.class
            ),
            this.parameters
        ).query().getSingleResult();
    }
}
