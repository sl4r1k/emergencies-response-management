package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Reporter;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllReporterNames extends CrudOperation<Reporter, List<String>> {
    public GetAllReporterNames(Db db) {
        super(db);
    }

    @Override
    public List<String> perform() {
        return new TupleCriteria<>(
            (criteria, query, reporter) -> query.multiselect(reporter.get("name")),
            this.db, Reporter.class
        ).query().getResultList().stream()
            .map(tuple -> tuple.get(0, String.class))
            .collect(Collectors.toList());
    }
}
