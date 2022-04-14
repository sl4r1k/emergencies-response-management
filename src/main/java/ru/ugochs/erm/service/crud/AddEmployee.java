package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.Parameter;
import ru.ugochs.erm.service.SeparatedParametersWithAnd;

public class AddEmployee extends AddIfNotExistsByAttributes<Employee> {
    public AddEmployee(Employee employee, Db db) {
        super(
            employee,
            new SeparatedParametersWithAnd(
                new Parameter("lastName", employee.getLastName()),
                new Parameter("firstName", employee.getFirstName()),
                new Parameter("middleName", employee.getMiddleName())
            ),
            "Сотрудник с таким ФИО уже существует",
            db
        );
    }
}
