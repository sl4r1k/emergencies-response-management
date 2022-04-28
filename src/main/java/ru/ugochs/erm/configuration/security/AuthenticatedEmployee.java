package ru.ugochs.erm.configuration.security;

import lombok.experimental.Delegate;
import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetEmployeeByLogin;

public class AuthenticatedEmployee extends Employee {
    @Delegate
    private final Employee employee;

    public AuthenticatedEmployee(Db db) {
        this.employee = new GetEmployeeByLogin(
            new CurrentAuthentication().getName(),
            db
        ).perform().get();
    }
}
