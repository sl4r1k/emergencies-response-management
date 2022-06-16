package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import java.util.Locale;

public class RussianDateTimePicker extends DateTimePicker {
    public RussianDateTimePicker(String label) {
        super(label);
        this.setDatePickerI18n(new DatePickerRussianI18n());
        this.setLocale(new Locale("ru"));
    }
}
