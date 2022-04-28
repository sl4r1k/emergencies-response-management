package ru.ugochs.erm.view.form;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.convert.StringAsRole;

public abstract class EmployeeForm extends Form<Employee> {
    protected final ComboBox<String> role;

    protected EmployeeForm(Db db) {
        super(db, "Сотрудник", Employee.class);
        this.role = new StandardBindedComboBox<>(
            "Роль",
            this.binder,
            new StringAsRole(),
            Employee::getRole,
            Employee::setRole
        );
        this.add(
            new StandardFormLayout(
                new BindedTextField<>(
                    "Фамилия",
                    this.binder,
                    Employee::getLastName,
                    Employee::setLastName
                ),
                new BindedTextField<>(
                    "Имя",
                    this.binder,
                    Employee::getFirstName,
                    Employee::setFirstName
                ),
                new BindedTextField<>(
                    "Отчество",
                    this.binder,
                    Employee::getMiddleName,
                    Employee::setMiddleName
                ),
                this.role,
                new BindedTextField<>(
                    "Логин",
                    this.binder,
                    Employee::getLogin,
                    Employee::setLogin
                ),
                new BindedPasswordField<>(
                    "Пароль",
                    this.binder,
                    Employee::getPassword,
                    Employee::setPassword
                )
            )
        );
    }
}
