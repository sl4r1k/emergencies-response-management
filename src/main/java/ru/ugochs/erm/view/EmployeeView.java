package ru.ugochs.erm.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.component.*;

@PageTitle("Сотрудники")
@Route("employees")
public class EmployeeView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final Grid<Employee> employees;

    public EmployeeView(Db db) {
        this.db = db;
        this.employees = new StandardGrid<>(
            new GridColumns<>(
                new GridColumn<>(
                    Employee::fullName,
                    "Сотрудник"
                ),
                new GridColumn<>(
                    Employee -> Employee.getRole().russianName(),
                    "Роль"
                )
            ),
            null
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Сотрудники"),
                new Button(
                    "Добавить",
                    (Component) null
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
