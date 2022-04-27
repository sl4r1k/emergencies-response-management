package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Employee;

public class GetEmployeeByFullName extends GetSingle<Employee> {
    public GetEmployeeByFullName(String name, Db db) {
        super("fullName", name, db, Employee.class);
    }
}
