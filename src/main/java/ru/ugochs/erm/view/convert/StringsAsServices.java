package ru.ugochs.erm.view.convert;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.ugochs.erm.entity.Service;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetServiceByName;
import java.util.Set;
import java.util.stream.Collectors;

public class StringsAsServices implements Converter<Set<String>, Set<Service>> {
    private final Db db;

    public StringsAsServices(Db db) {
        this.db = db;
    }

    @Override
    public Result<Set<Service>> convertToModel(Set<String> names, ValueContext context) {
        return Result.ok(
            names.stream()
                .map(
                    name -> new GetServiceByName(
                        name,
                        this.db
                    ).perform()
                        .orElse(null)
                )
                .collect(Collectors.toSet())
        );
    }

    @Override
    public Set<String> convertToPresentation(Set<Service> services, ValueContext context) {
        return services.stream()
            .map(Service::getName)
            .collect(Collectors.toSet());
    }
}
