package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.datepicker.DatePicker;
import java.time.LocalDate;

public class RussianDatePicker extends DatePicker {
    public RussianDatePicker(String label) {
        this(label, null);
    }

    public RussianDatePicker(String label, LocalDate value) {
        super(label, value);
        this.setI18n(new DatePickerRussianI18n());
    }
}
