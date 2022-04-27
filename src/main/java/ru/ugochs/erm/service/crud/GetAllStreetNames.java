package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Street;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllStreetNames extends CrudOperation<Street, List<String>> {
    public GetAllStreetNames(Db db) {
        super(db);
    }

    @Override
    public List<String> perform() {
        return new TupleCriteria<>(
            (criteria, query, street) -> query.multiselect(street.get("name")),
            this.db, Street.class
        ).query().getResultList().stream()
            .map(tuple -> tuple.get(0, String.class))
            .collect(Collectors.toList());
    }
}
