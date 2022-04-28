package ru.ugochs.erm.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.add.AddEmployeeForm;
import ru.ugochs.erm.view.form.edit.EditEmployeeForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@PageTitle("Сотрудники")
@Route(value = "employees", layout = MainLayout.class)
public class EmployeeView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final Grid<Employee> employees;

    public EmployeeView(Db db) {
        this.db = db;
        this.employees = new StandardGrid<>(
            new GridColumns<>(
                new ValueGridColumn<>(
                    Employee::getFullName,
                    "Сотрудник"
                ),
                new ValueGridColumn<>(
                    Employee -> Employee.getRole().russianName(),
                    "Роль"
                )
            ),
            clickEvent -> new NavigationByEntity(
                clickEvent.getItem(),
                EditEmployeeForm.class
            ).perform()
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Сотрудники"),
                new Button(
                    "Добавить",
                    event -> new Navigation(
                        AddEmployeeForm.class
                    ).perform()
                )
            ),
            this.employees
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.employees.setItems(
            new GetAll<>(
                this.db,
                Employee.class
            ).perform()
        );
    }
}
