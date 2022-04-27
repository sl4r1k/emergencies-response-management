package ru.ugochs.erm.view.convert;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.ugochs.erm.entity.Service;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetServiceByName;
import java.util.Objects;

public class StringAsService implements Converter<String, Service> {
    private final Db db;

    public StringAsService(Db db) {
        this.db = db;
    }

    @Override
    public Result<Service> convertToModel(String serviceName, ValueContext context) {
        return Result.ok(
            new GetServiceByName(
                serviceName,
                this.db
            ).perform().orElse(null)
        );
    }

    @Override
    public String convertToPresentation(Service service, ValueContext context) {
        return Objects.isNull(service)
            ? ""
            : service.getName();
    }
}
