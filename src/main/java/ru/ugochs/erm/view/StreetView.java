package ru.ugochs.erm.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Street;
import ru.ugochs.erm.service.AddressesImport;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.add.AddStreetForm;
import ru.ugochs.erm.view.form.edit.EditStreetForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@PageTitle("Улицы")
@Route(value = "streets", layout = MainLayout.class)
public class StreetView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final Grid<Street> streets;

    public StreetView(Db db) {
        this.db = db;
        this.streets = new StandardGrid<>(
            new GridColumns<>(
                new ValueGridColumn<>(
                    Street::getName,
                    "Улица"
                ),
                new ValueGridColumn<>(
                    street -> street.getDistrict().getName(),
                    "Район"
                )
            ),
            clickEvent -> new NavigationByEntity(
                clickEvent.getItem(),
                EditStreetForm.class
            ).perform()
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Улицы"),
                new Button(
                    "Добавить",
                    event -> new Navigation(
                        AddStreetForm.class
                    ).perform()
                ),
                new StandardUpload(
                    new MemoryBuffer(),
                    buffer -> event -> {
                        new AddressesImport(
                            buffer.getInputStream(),
                            this.db
                        ).act();
                        UI.getCurrent().getPage().reload();
                    }
                )
            ),
            this.streets
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.streets.setItems(
            new GetAll<>(
                this.db,
                Street.class
            ).perform());
    }
}
