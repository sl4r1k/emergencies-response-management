package ru.ugochs.erm.view.form;

import com.vaadin.flow.component.combobox.ComboBox;
import ru.ugochs.erm.entity.Index;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.convert.StringAsIndex;

public abstract class IndexForm extends Form<Index> {
    protected final ComboBox<String> parentIndex;

    protected IndexForm(Db db) {
        super(db, "Индекс", Index.class);
        this.parentIndex = new StandardBindedComboBox<>(
            "Внешний индекс",
            this.binder,
            new StringAsIndex(db),
            Index::getParent,
            Index::setParent
        );
        this.add(
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
