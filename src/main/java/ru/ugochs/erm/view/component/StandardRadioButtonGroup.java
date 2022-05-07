package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import java.util.Collection;

public class StandardRadioButtonGroup extends RadioButtonGroup<String> {
    public StandardRadioButtonGroup(String label, Collection<String> values) {
        this(label, values, null);
    }

    public StandardRadioButtonGroup(String label, Collection<String> values, String value) {
        this.setLabel(label);
        this.setItems(values);
        this.setValue(value);
    }
}
