package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.function.ValueProvider;
import ru.ugochs.erm.entity.AbstractEntity;
import java.util.Collections;

public class StandardBindedComboBox<E extends AbstractEntity> extends ComboBox<String> {
    public StandardBindedComboBox(String label, Binder<E> binder, Converter<String, E> converter,
                                  ValueProvider<E, E> getter, Setter<E, E> setter) {
        super(label);
        this.setItems(Collections.emptyList());
        this.setAllowCustomValue(false);
        binder.forField(this)
            .withConverter(converter)
            .bind(getter, setter);
    }
}
