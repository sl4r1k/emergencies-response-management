package ru.ugochs.erm.view.form;

import ru.ugochs.erm.entity.District;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.BindedTextField;
import ru.ugochs.erm.view.component.StandardFormLayout;

public abstract class DistrictForm extends Form<District> {
    protected DistrictForm(Db db) {
        super(db, "Район", District.class);
        this.add(
            new StandardFormLayout(
                new BindedTextField<>(
                    "Название",
                    this.binder,
                    District::getName,
                    District::setName
                )
            )
        );
    }
}
