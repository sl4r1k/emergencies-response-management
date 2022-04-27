package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.textfield.IntegerField;

public class WidthedIntegerField extends IntegerField {
    public WidthedIntegerField(String label, float width) {
        this.setLabel(label);
        this.setMin(0);
        this.setValue(0);
        this.setHasControls(true);
        this.setWidth(width, Unit.PERCENTAGE);
    }
}
