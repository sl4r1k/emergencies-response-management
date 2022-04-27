package ru.ugochs.erm.view.convert;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetEmployeeByFullName;
import java.util.Objects;

public class StringAsEmployee implements Converter<String, Employee> {
    private final Db db;

    public StringAsEmployee(Db db) {
        this.db = db;
    }

    @Override
    public Result<Employee> convertToModel(String fullName, ValueContext context) {
        return Result.ok(
            new GetEmployeeByFullName(
                fullName,
                this.db
            ).perform().orElse(null)
        );
    }

    @Override
    public String convertToPresentation(Employee employee, ValueContext context) {
        return Objects.isNull(employee)
            ? ""
            : employee.getFullName();
    }
}
