package ru.ugochs.erm.view.form;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.ugochs.erm.entity.Street;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.convert.StringAsDistrict;

public abstract class StreetForm extends Form<Street> {
    protected final ComboBox<String> district;

    protected StreetForm(Db db) {
        super(db, "Улица", Street.class);
        this.district = new StandardBindedComboBox<>(
            "Район",
            this.binder,
            new StringAsDistrict(db),
            Street::getDistrict,
            Street::setDistrict
        );
        this.add(
            new StandardFormLayout(
                this.district,
                new BindedTextField<>(
                    "Название",
                    this.binder,
                    Street::getName,
                    Street::setName
                )
            )
        );
    }
}
