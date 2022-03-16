package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;
import java.util.List;

public class GetAllIndexesByLevel1 extends GetAll<Index> {
    public GetAllIndexesByLevel1(Db db) {
        super(db, Index.class);
    }

    @Override
    public List<Index> perform() {
        return new EntityCriteria<>(
            (criteria, query, entity) -> query.where(criteria.equal(
                entity.get("level"), 1
            )),
            this.db, Index.class
        ).query().getResultList();
    }
}
