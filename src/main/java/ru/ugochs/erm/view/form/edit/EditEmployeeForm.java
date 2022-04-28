package ru.ugochs.erm.view.form.edit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.entity.Role;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.*;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.EmployeeForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@Route(value = "employees/:id/edit", layout = MainLayout.class)
public class EditEmployeeForm extends EmployeeForm implements BeforeEnterObserver {
    public EditEmployeeForm(Db db) {
        super(db);
        this.add(
            new HorizontalLayout(
                new EditButton<>(
                    employee -> new EditEmployee(employee, this.db),
                    this.binder,
                    EmployeeView.class
                ),
                new RemoveButton<>(
                    employee -> new Remove<>(employee, this.db),
                    this.binder,
                    EmployeeView.class
                ),
                new CancelButton(EmployeeView.class)
            )
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        new GetById<>(
            new IdFromRoute(
                event.getRouteParameters()
            ).value(),
            this.db,
            Employee.class
        ).perform().ifPresentOrElse(
            employee -> {
                this.binder.setBean(employee);
                String role = this.role.getValue();
                this.role.setItems(Role.russianNames());
                this.role.setValue(role);
            },
            () -> event.rerouteToError(
                NotFoundException.class,
                "Нет такого индекса"
            )
        );
    }
}
