package ru.ugochs.erm.view.form.edit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.*;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.EmergencyForm;
import java.util.Set;

@Route(value = "emergencies/:id/edit", layout = MainLayout.class)
public class EditEmergencyForm extends EmergencyForm implements BeforeEnterObserver {
    public EditEmergencyForm(Db db) {
        super(db);
        this.add(
            new HorizontalLayout(
                new EditButton<>(
                    emergency -> new Edit<>(emergency, this.db),
                    this.binder,
                    EmergencyView.class
                ),
                new RemoveButton<>(
                    emergency -> new Remove<>(emergency, this.db),
                    this.binder,
                    EmergencyView.class
                ),
                new CancelButton(EmergencyView.class)
            )
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        new GetById<>(
            new IdFromRoute(event.getRouteParameters()).value(),
            this.db,
            Emergency.class
        ).perform().ifPresentOrElse(
            emergency -> {
                this.binder.setBean(emergency);
                String index = this.index.getValue();
                this.index.setItems(
                    new GetAllIndexNamesByLevel3(this.db).perform()
                );
                this.index.setValue(index);
                String reporter = this.reporter.getValue();
                this.reporter.setItems(
                    new GetAllReporterNames(this.db).perform()
                );
                this.reporter.setValue(reporter);
                String street = this.street.getValue();
                this.street.setItems(
                    new GetAllStreetNames(this.db).perform()
                );
                this.street.setValue(street);
                Set<String> services = this.services.getSelectedItems();
                this.services.setItems(
                    new GetAllServiceNames(this.db).perform()
                );
                this.services.select(services);
            },
            () -> event.rerouteToError(
                NotFoundException.class,
                "Нет такого индекса"
            )
        );
    }
}
