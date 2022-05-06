package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.datepicker.DatePicker;
import ru.ugochs.erm.view.util.RussianMonthNames;
import ru.ugochs.erm.view.util.RussianWeekDayNames;
import java.time.format.TextStyle;

public class DatePickerRussianI18n extends DatePicker.DatePickerI18n {
    public DatePickerRussianI18n() {
        this.setMonthNames(
            new RussianMonthNames(
                TextStyle.FULL_STANDALONE
            ).list()
        );
        this.setWeekdays(
            new RussianWeekDayNames(
                TextStyle.FULL_STANDALONE
            ).list()
        );
        this.setWeekdaysShort(
            new RussianWeekDayNames(
                TextStyle.SHORT_STANDALONE
            ).list()
        );
        this.setFirstDayOfWeek(1);
        this.setWeek("Неделя");
        this.setToday("Сегодня");
        this.setCancel("Отмена");
    }
}
