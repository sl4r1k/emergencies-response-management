package ru.ugochs.erm.view.form.add;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.EmergencyView;
import ru.ugochs.erm.view.MainLayout;
import ru.ugochs.erm.view.component.AddButton;
import ru.ugochs.erm.view.component.CancelButton;
import ru.ugochs.erm.view.form.EmergencyForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed({"ADMIN", "USER"})
@Route(value = "emergencies/add", layout = MainLayout.class)
public class AddEmergencyForm extends EmergencyForm {
    public AddEmergencyForm(Db db) {
        super(db);
        this.binder.setBean(new Emergency());
        this.index.setItems(
            new GetAllIndexNamesByLevel3(this.db).perform()
        );
        this.reporter.setItems(
            new GetAllReporterNames(this.db).perform()
        );
        this.street.setItems(
            new GetAllStreetNames(this.db).perform()
        );
        this.services.setItems(
            new GetAllServiceNames(this.db).perform()
        );
        this.add(
            new HorizontalLayout(
                new AddButton<>(
                    emergency -> new Add<>(emergency, this.db),
                    this.binder,
                    EmergencyView.class
                ),
                new CancelButton(EmergencyView.class)
            )
        );
    }
}
