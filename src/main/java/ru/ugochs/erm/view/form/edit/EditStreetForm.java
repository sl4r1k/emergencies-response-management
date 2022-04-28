package ru.ugochs.erm.view.form.edit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Street;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.*;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.StreetForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@Route(value = "streets/:id/edit", layout = MainLayout.class)
public class EditStreetForm extends StreetForm implements BeforeEnterObserver {
    public EditStreetForm(Db db) {
        super(db);
        this.add(
            new HorizontalLayout(
                new EditButton<>(
                    street -> new EditStreet(street, this.db),
                    this.binder,
                    StreetView.class
                ),
                new RemoveButton<>(
                    street -> new Remove<>(street, this.db),
                    this.binder,
                    StreetView.class
                ),
                new CancelButton(StreetView.class)
            )
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        new GetById<>(
            new IdFromRoute(event.getRouteParameters()).value(),
            this.db,
            Street.class
        ).perform().ifPresentOrElse(
            street -> {
                this.binder.setBean(street);
                String district = this.district.getValue();
                this.district.setItems(
                    new GetAllDistrictNames(this.db).perform()
                );
                this.district.setValue(district);
            },
            () -> event.rerouteToError(
                NotFoundException.class,
                "Нет такого индекса"
            )
        );
    }
}
