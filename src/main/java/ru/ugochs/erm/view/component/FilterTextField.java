package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class FilterTextField extends TextField {
    public FilterTextField(ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> event) {
        this.addValueChangeListener(event);
        this.setValueChangeMode(ValueChangeMode.EAGER);
        this.setPlaceholder("Поиск");
        this.setClearButtonVisible(true);
    }
}
