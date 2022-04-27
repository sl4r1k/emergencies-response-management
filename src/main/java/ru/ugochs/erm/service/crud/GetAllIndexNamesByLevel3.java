package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllIndexNamesByLevel3 extends CrudOperation<Index, List<String>> {
    public GetAllIndexNamesByLevel3(Db db) {
        super(db);
    }

    @Override
    public List<String> perform() {
        return new TupleCriteria<>(
            (criteria, query, index) -> query.multiselect(index.get("name")).where(
                criteria.equal(
                    index.get("level"), 3
                )
            ),
            this.db, Index.class
        ).query().getResultList().stream()
            .map(tuple -> tuple.get(0, String.class))
            .collect(Collectors.toList());
    }
}
