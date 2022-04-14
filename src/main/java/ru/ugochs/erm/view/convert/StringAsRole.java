package ru.ugochs.erm.view.convert;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.ugochs.erm.entity.Role;
import java.util.Objects;

public class StringAsRole implements Converter<String, Role> {
    @Override
    public Result<Role> convertToModel(String roleName, ValueContext context) {
        return Result.ok(Role.valueOfRussianName(roleName));
    }

    @Override
    public String convertToPresentation(Role role, ValueContext context) {
        return Objects.isNull(role)
            ? ""
            : role.russianName();
    }
}
