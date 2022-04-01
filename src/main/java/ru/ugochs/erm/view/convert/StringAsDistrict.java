package ru.ugochs.erm.view.convert;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.ugochs.erm.entity.District;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetDistrictByName;
import java.util.Objects;

public class StringAsDistrict implements Converter<String, District> {
    private final Db db;

    public StringAsDistrict(Db db) {
        this.db = db;
    }

    @Override
    public Result<District> convertToModel(String district, ValueContext context) {
        return Result.ok(
            new GetDistrictByName(
                district,
                this.db
            ).perform().orElse(null)
        );
    }

    @Override
    public String convertToPresentation(District index, ValueContext context) {
        return Objects.isNull(index)
            ? ""
            : index.getName();
    }
}
