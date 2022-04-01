package ru.ugochs.erm.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Service;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.add.AddServiceForm;
import ru.ugochs.erm.view.form.edit.EditServiceForm;

@PageTitle("Службы")
@Route("services")
public class ServiceView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final Grid<Service> services;

    public ServiceView(Db db) {
        this.db = db;
        this.services = new StandardGrid<>(
            new GridColumns<>(
                new GridColumn<>(Service::getName)
            ),
            clickEvent -> new NavigationByEntity(
                clickEvent.getItem(),
                EditServiceForm.class
            ).perform()
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Службы"),
                new Button(
                    "Добавить",
                    event -> new Navigation(
                        AddServiceForm.class
                    ).perform()
                )
            ),
            this.services
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.services.setItems(
            new GetAll<>(
                this.db,
                Service.class
            ).perform()
        );
    }
}
