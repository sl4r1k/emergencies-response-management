package ru.ugochs.erm.service.crud;

import org.apache.commons.lang3.function.TriFunction;
import ru.ugochs.erm.entity.AbstractEntity;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public abstract class Criteria<E extends AbstractEntity, T> {
    protected final TriFunction<CriteriaBuilder, CriteriaQuery<T>, Root<E>, CriteriaQuery<T>> action;
    protected final Db db;
    protected final Class<E> type;
    protected final CriteriaBuilder criteria;

    protected Criteria(
        TriFunction<CriteriaBuilder, CriteriaQuery<T>, Root<E>, CriteriaQuery<T>> action,
        Db db, Class<E> type
    ) {
        this.action = action;
        this.db = db;
        this.type = type;
        this.criteria = db.getCriteriaBuilder();
    }

    public abstract TypedQuery<T> query();
}
