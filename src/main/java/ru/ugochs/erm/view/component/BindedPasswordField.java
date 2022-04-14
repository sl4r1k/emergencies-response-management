package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import ru.ugochs.erm.entity.AbstractEntity;

public class BindedPasswordField<T extends AbstractEntity> extends PasswordField {
    public BindedPasswordField(
        String label,
        Binder<T> binder,
        ValueProvider<T, String> getter,
        Setter<T, String> setter
    ) {
        super(label);
        binder.forField(this)
            .asRequired("Заполните поле")
            .bind(getter, setter);
    }
}
