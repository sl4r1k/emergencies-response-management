package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Service;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllServiceNames extends CrudOperation<Service, List<String>> {
    public GetAllServiceNames(Db db) {
        super(db);
    }

    @Override
    public List<String> perform() {
        return new TupleCriteria<>(
            (criteria, query, service) -> query.multiselect(service.get("name")),
            this.db, Service.class
        ).query().getResultList().stream()
            .map(tuple -> tuple.get(0, String.class))
            .collect(Collectors.toList());
    }
}
