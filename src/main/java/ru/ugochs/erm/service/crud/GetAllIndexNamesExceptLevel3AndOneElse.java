package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllIndexNamesExceptLevel3AndOneElse extends CrudOperation<Index, List<String>> {
    private final Index index;

    public GetAllIndexNamesExceptLevel3AndOneElse(Index index, Db db) {
        super(db);
        this.index = index;
    }

    @Override
    public List<String> perform() {
        return new TupleCriteria<>(
            (criteria, query, index) ->
                query.multiselect(index.get("name")).where(
                    criteria.and(
                        criteria.notEqual(
                            index.get("level"), 3
                        ),
                        criteria.notEqual(
                            index.get("id"),
                            this.index.getId()
                        )
                    )
                ),
            this.db, Index.class
        ).query().getResultList().stream()
            .map(tuple -> tuple.get(0, String.class))
            .collect(Collectors.toList());
    }
}
