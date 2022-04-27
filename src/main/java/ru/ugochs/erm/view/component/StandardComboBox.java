package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.combobox.ComboBox;
import java.util.Collection;
import java.util.Collections;

public class StandardComboBox extends ComboBox<String> {
    public StandardComboBox(String label) {
        this(label, Collections.emptyList());
    }

    public StandardComboBox(String label, Collection<String> items) {
        this(label, items, null);
    }

    public StandardComboBox(String label, Collection<String> items, String value) {
        super(label);
        this.setItems(items);
        this.setValue(value);
        this.setAllowCustomValue(false);
    }
}
