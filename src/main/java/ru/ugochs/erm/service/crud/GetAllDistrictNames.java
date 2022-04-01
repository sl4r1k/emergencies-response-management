package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.District;
import ru.ugochs.erm.entity.Index;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllDistrictNames extends CrudOperation<Index, List<String>> {
    public GetAllDistrictNames(Db db) {
        super(db);
    }

    @Override
    public List<String> perform() {
        return new TupleCriteria<>(
            (criteria, query, district) -> query.multiselect(district.get("name")),
            this.db, District.class
        ).query().getResultList().stream()
            .map(tuple -> tuple.get(0, String.class))
            .collect(Collectors.toList());
    }
}
