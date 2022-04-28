package ru.ugochs.erm.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.District;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.add.AddDistrictForm;
import ru.ugochs.erm.view.form.edit.EditDistrictForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@PageTitle("Районы")
@Route(value = "districts", layout = NavigationMenu.class)
public class DistrictView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final Grid<District> districts;

    public DistrictView(Db db) {
        this.db = db;
        this.districts = new StandardGrid<>(
            new GridColumns<>(
                new ValueGridColumn<>(District::getName)
            ),
            clickEvent -> new NavigationByEntity(
                clickEvent.getItem(),
                EditDistrictForm.class
            ).perform()
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Районы"),
                new Button(
                    "Добавить",
                    event -> new Navigation(AddDistrictForm.class).perform()
                )
            ),
            this.districts
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.districts.setItems(
            new GetAll<>(
                this.db,
                District.class
            ).perform()
        );
    }
}
