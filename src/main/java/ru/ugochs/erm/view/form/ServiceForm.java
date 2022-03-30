package ru.ugochs.erm.view.form;

import ru.ugochs.erm.entity.Service;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.BindedTextField;
import ru.ugochs.erm.view.component.StandardFormLayout;

public abstract class ServiceForm extends Form<Service> {
    protected ServiceForm(Db db) {
        super(db, "Служба", Service.class);
        this.add(
            new StandardFormLayout(
                new BindedTextField<>(
                    "Название",
                    this.binder,
                    Service::getName,
                    Service::setName
                )
            )
        );
    }
}
