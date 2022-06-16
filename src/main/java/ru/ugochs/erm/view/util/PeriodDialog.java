package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import ru.ugochs.erm.view.component.*;
import java.time.LocalDate;
import java.util.function.BiConsumer;

public class PeriodDialog extends Dialog {
    public PeriodDialog(BiConsumer<LocalDate, LocalDate> route) {
        RussianDatePicker from = new RussianDatePicker("С");
        RussianDatePicker to = new RussianDatePicker("По");
        this.add(
            new CentredVerticalLayout(
                from, to,
                new Button(
                    "Сформировать",
                    event -> {
                        if (from.isEmpty()) {
                            new ErrorNotification(
                                "Начальная дата должна быть указана"
                            ).open();
                            return;
                        }
                        this.close();
                        route.accept(
                            from.getValue(),
                            to.getOptionalValue()
                                .orElse(LocalDate.now())
                        );
                    }
                )
            )
        );
    }
}
