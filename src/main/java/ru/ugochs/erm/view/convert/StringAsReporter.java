package ru.ugochs.erm.view.convert;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.ugochs.erm.entity.Reporter;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetReporterByName;
import java.util.Objects;

public class StringAsReporter implements Converter<String, Reporter> {
    private final Db db;

    public StringAsReporter(Db db) {
        this.db = db;
    }

    @Override
    public Result<Reporter> convertToModel(String reporterName, ValueContext context) {
        return Result.ok(
            new GetReporterByName(
                reporterName,
                this.db
            ).perform().orElse(null)
        );
    }

    @Override
    public String convertToPresentation(Reporter reporter, ValueContext context) {
        return Objects.isNull(reporter)
            ? ""
            : reporter.getName();
    }
}
