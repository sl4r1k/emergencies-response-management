package ru.ugochs.erm.configuration.security;

import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.crud.*;
import java.util.Optional;

public class AuthenticatedEmployee extends CrudOperation<Employee, Optional<Employee>> {
    public AuthenticatedEmployee(Db db) {
        super(db);
    }

    @Override
    public Optional<Employee> perform() {
        return new GetEmployeeByLogin(
            new CurrentAuthentication().getName(),
            this.db
        ).perform();
    }
}
