package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;
import java.util.List;

public class GetAllIndexesByRelativeName extends GetAll<Index> {
    private final String name;

    public GetAllIndexesByRelativeName(String name, Db db) {
        super(db, Index.class);
        this.name = name;
    }

    @Override
    public List<Index> perform() {
        return new EntityCriteria<>(
            (criteria, query, index) -> query.select(index).where(
                criteria.like(
                    criteria.upper(index.get("name")),
                    criteria.upper(
                        criteria.literal("%" + this.name + "%")
                    )
                )
            ),
            this.db, Index.class
        ).query().getResultList();
    }
}
