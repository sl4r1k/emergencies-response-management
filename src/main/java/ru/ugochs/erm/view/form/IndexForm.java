package ru.ugochs.erm.view.form;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import ru.ugochs.erm.entity.Index;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.convert.StringAsIndex;

public abstract class IndexForm extends CentredVerticalLayout {
    protected final Db db;
    protected final Binder<Index> binder;
    protected final ComboBox<String> parentIndex;

    protected IndexForm(Db db) {
        this.db = db;
        this.binder = new BeanValidationBinder<>(Index.class);
        this.parentIndex = new StandardBindedComboBox<>(
            "Внешний индекс",
            this.binder,
            new StringAsIndex(db),
            Index::getParent,
            Index::setParent
        );
        this.add(
            new H3("Индекс"),
            new StandardFormLayout(
                this.parentIndex,
                new BindedTextField<>(
                    "Название",
                    this.binder,
                    Index::getName,
                    Index::setName
                )
            )
        );
    }
}
