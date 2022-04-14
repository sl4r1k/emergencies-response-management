package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.Parameter;
import ru.ugochs.erm.service.SeparatedParametersWithAnd;

public class EditEmployee extends EditIfNotExistsByAttributes<Employee> {
    public EditEmployee(Employee employee, Db db) {
        super(
            employee,
            new SeparatedParametersWithAnd(
                new Parameter("lastName", employee.getLastName()),
                new Parameter("firstName", employee.getFirstName()),
                new Parameter("middleName", employee.getMiddleName())
            ),
            employee.getId(),
            "Сотрудник с таким ФИО уже существует",
            db
        );
    }
}
