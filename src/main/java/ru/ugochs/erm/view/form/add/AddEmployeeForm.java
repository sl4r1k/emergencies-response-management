package ru.ugochs.erm.view.form.add;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.entity.Role;
import ru.ugochs.erm.service.crud.AddEmployee;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.EmployeeView;
import ru.ugochs.erm.view.component.AddButton;
import ru.ugochs.erm.view.component.CancelButton;
import ru.ugochs.erm.view.form.EmployeeForm;

@Route("employees/add")
public class AddEmployeeForm extends EmployeeForm {
    public AddEmployeeForm(Db db) {
        super(db);
        this.binder.setBean(new Employee());
        this.role.setItems(Role.russianNames());
        this.add(
            new HorizontalLayout(
                new AddButton<>(
                    employee -> new AddEmployee(employee, this.db),
                    this.binder,
                    EmployeeView.class
                ),
                new CancelButton(EmployeeView.class)
            )
        );
    }
}
