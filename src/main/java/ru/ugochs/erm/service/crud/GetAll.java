package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import java.util.List;

public class GetAll<T extends AbstractEntity> extends CrudOperation<T, List<T>> {
    protected final Class<T> type;

    public GetAll(Db db, Class<T> type) {
        super(db);
        this.type = type;
    }

    @Override
    public List<T> perform() {
        return new EntityCriteria<>(
            (criteria, query, entity) -> query.select(entity),
            this.db, this.type
        ).query().getResultList();
    }
}
