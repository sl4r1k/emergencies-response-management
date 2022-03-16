package ru.ugochs.erm.service.crud;

import org.apache.commons.lang3.function.TriFunction;
import ru.ugochs.erm.entity.AbstractEntity;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public class TupleCriteria<T extends AbstractEntity> extends Criteria<T, Tuple> {
    public TupleCriteria(
        TriFunction<CriteriaBuilder, CriteriaQuery<Tuple>, Root<T>, CriteriaQuery<Tuple>> action,
        Db db, Class<T> type
    ) {
        super(action, db, type);
    }

    @Override
    public TypedQuery<Tuple> query() {
        CriteriaQuery<Tuple> query = this.criteria.createTupleQuery();
        Root<T> entity = query.from(this.type);
        return this.db.createQuery(this.action.apply(this.criteria, query, entity));
    }
}
