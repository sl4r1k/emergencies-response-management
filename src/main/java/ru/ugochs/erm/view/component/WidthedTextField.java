package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.textfield.TextField;

public class WidthedTextField extends TextField {
    public WidthedTextField(String label, float width) {
        super(label);
        this.setWidth(width, Unit.PERCENTAGE);
    }
}
