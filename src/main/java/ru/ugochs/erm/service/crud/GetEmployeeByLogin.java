package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Employee;

public class GetEmployeeByLogin extends GetSingle<Employee> {
    public GetEmployeeByLogin(String login, Db db) {
        super("login", login, db, Employee.class);
    }
}
