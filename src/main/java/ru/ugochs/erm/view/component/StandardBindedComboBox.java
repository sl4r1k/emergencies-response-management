package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.function.ValueProvider;
import ru.ugochs.erm.entity.AbstractEntity;
import java.util.Collections;

public class StandardBindedComboBox<E1, E2 extends AbstractEntity> extends ComboBox<String> {
    public StandardBindedComboBox(
        String label, Binder<E2> binder,
        Converter<String, E1> converter,
        ValueProvider<E2, E1> getter,
        Setter<E2, E1> setter
    ) {
        super(label);
        this.setItems(Collections.emptyList());
        this.setAllowCustomValue(false);
        binder.forField(this)
            .withConverter(converter)
            .bind(getter, setter);
    }
}
