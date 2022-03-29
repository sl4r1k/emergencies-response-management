package ru.ugochs.erm.view.form;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import ru.ugochs.erm.entity.Reporter;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.*;

public abstract class ReporterForm extends CentredVerticalLayout {
    protected final Db db;
    protected final Binder<Reporter> binder;

    protected ReporterForm(Db db) {
        this.db = db;
        this.binder = new BeanValidationBinder<>(Reporter.class);
        this.add(
            new H3("Заявитель"),
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
