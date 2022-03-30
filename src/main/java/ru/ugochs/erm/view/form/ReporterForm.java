package ru.ugochs.erm.view.form;

import ru.ugochs.erm.entity.Reporter;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.BindedTextField;
import ru.ugochs.erm.view.component.StandardFormLayout;

public abstract class ReporterForm extends Form<Reporter> {

    protected ReporterForm(Db db) {
        super(db, "Заявитель", Reporter.class);
        this.add(
            new StandardFormLayout(
                new BindedTextField<>(
                    "Название",
                    this.binder,
                    Reporter::getName,
                    Reporter::setName
                )
            )
        );
    }
}
