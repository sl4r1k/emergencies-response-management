package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import ru.ugochs.erm.view.component.DatePickerRussianI18n;

public class RussianDateTimePicker extends DateTimePicker {
    public RussianDateTimePicker(String label) {
        super(label);
        this.setDatePickerI18n(new DatePickerRussianI18n());
    }
}
