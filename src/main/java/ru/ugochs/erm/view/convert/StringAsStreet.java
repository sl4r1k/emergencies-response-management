package ru.ugochs.erm.view.convert;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.ugochs.erm.entity.Street;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetStreetByName;
import java.util.Objects;

public class StringAsStreet implements Converter<String, Street> {
    private final Db db;

    public StringAsStreet(Db db) {
        this.db = db;
    }

    @Override
    public Result<Street> convertToModel(String streetName, ValueContext context) {
        return Result.ok(
            new GetStreetByName(
                streetName,
                this.db
            ).perform().orElse(null)
        );
    }

    @Override
    public String convertToPresentation(Street street, ValueContext context) {
        return Objects.isNull(street)
            ? ""
            : street.getName();
    }
}
