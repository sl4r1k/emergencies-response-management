package ru.ugochs.erm.view.form.add;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import ru.ugochs.erm.entity.Service;
import ru.ugochs.erm.service.crud.AddService;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.ServiceView;
import ru.ugochs.erm.view.component.AddButton;
import ru.ugochs.erm.view.component.CancelButton;
import ru.ugochs.erm.view.form.ServiceForm;

@Route("services/add")
public class AddServiceForm extends ServiceForm {
    public AddServiceForm(Db db) {
        super(db);
        this.binder.setBean(new Service());
        this.add(
            new HorizontalLayout(
                new AddButton<>(
                    service -> new AddService(service, this.db),
                    this.binder,
                    ServiceView.class
                ),
                new CancelButton(ServiceView.class)
            )
        );
    }
}
