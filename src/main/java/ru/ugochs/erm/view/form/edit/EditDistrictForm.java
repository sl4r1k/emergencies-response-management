package ru.ugochs.erm.view.form.edit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.District;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.*;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.DistrictForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@Route(value = "districts/:id/edit", layout = MainLayout.class)
public class EditDistrictForm extends DistrictForm implements BeforeEnterObserver {
    public EditDistrictForm(Db db) {
        super(db);
        this.add(
            new HorizontalLayout(
                new EditButton<>(
                    district -> new EditDistrict(district, this.db),
                    this.binder,
                    DistrictView.class
                ),
                new RemoveButton<>(
                    district -> new Remove<>(district, this.db),
                    this.binder,
                    DistrictView.class
                ),
                new CancelButton(DistrictView.class)
            )
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        new GetById<>(
            new IdFromRoute(event.getRouteParameters()).value(),
            this.db,
            District.class
        ).perform().ifPresentOrElse(
            this.binder::setBean,
            () -> event.rerouteToError(
                NotFoundException.class,
                "Нет такого индекса"
            )
        );
    }
}
