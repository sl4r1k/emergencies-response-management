package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import javax.persistence.NoResultException;
import java.util.Optional;

public abstract class GetSingle<T extends AbstractEntity> extends CrudOperation<T, Optional<T>> {
    private final String attribute;
    private final Object value;
    private final Class<T> type;

    protected GetSingle(String attribute, Object value, Db db, Class<T> type) {
        super(db);
        this.attribute = attribute;
        this.value = value;
        this.type = type;
    }

    @Override
    public Optional<T> perform() {
        try {
            return Optional.of(
                new EntityCriteria<>((criteria, query, entity) -> query.select(entity).where(
                    criteria.equal(
                        entity.get(this.attribute), this.value
                    )
                ),
                    this.db, this.type
                ).query().getSingleResult()
            );
        } catch (NoResultException exception) {
            return Optional.empty();
        }
    }
}
