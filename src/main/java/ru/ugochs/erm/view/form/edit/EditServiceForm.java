package ru.ugochs.erm.view.form.edit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Service;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.*;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.ServiceForm;
import ru.ugochs.erm.view.component.NavigationMenu;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@Route(value = "services/:id/edit", layout = NavigationMenu.class)
public class EditServiceForm extends ServiceForm implements BeforeEnterObserver {
    public EditServiceForm(Db db) {
        super(db);
        this.add(
            new HorizontalLayout(
                new EditButton<>(
                    service -> new EditService(service, this.db),
                    this.binder,
                    ServiceView.class
                ),
                new RemoveButton<>(
                    service -> new RemoveService(service, this.db),
                    this.binder,
                    ServiceView.class
                ),
                new CancelButton(ServiceView.class)
            )
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        new GetById<>(
            new IdFromRoute(event.getRouteParameters()).value(),
            this.db,
            Service.class
        ).perform().ifPresentOrElse(
            this.binder::setBean,
            () -> event.rerouteToError(
                NotFoundException.class,
                "Нет такого индекса"
            )
        );
    }
}
