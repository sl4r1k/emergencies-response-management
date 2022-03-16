package ru.ugochs.erm.service.crud;

import org.apache.commons.lang3.function.TriFunction;
import ru.ugochs.erm.entity.AbstractEntity;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public class EntityCriteria<T extends AbstractEntity> extends Criteria<T, T> {
    public EntityCriteria(
        TriFunction<CriteriaBuilder, CriteriaQuery<T>, Root<T>, CriteriaQuery<T>> action,
        Db db, Class<T> type
    ) {
        super(action, db, type);
    }

    @Override
    public TypedQuery<T> query() {
        CriteriaQuery<T> query = this.criteria.createQuery(this.type);
        Root<T> entity = query.from(this.type);
        return this.db.createQuery(this.action.apply(this.criteria, query, entity));
    }
}
