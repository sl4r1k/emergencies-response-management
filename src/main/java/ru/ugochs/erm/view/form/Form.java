package ru.ugochs.erm.view.form;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.CentredVerticalLayout;

public abstract class Form<T extends AbstractEntity> extends CentredVerticalLayout {
    protected final Db db;
    protected final Binder<T> binder;

    public Form(Db db, String title, Class<T> type) {
        super(new H3(title));
        this.db = db;
        this.binder = new BeanValidationBinder<>(type);
    }
}
