package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.data.binder.Binder;
import org.claspina.confirmdialog.ButtonOption;
import org.claspina.confirmdialog.ConfirmDialog;
import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.crud.Remove;
import ru.ugochs.erm.view.Navigation;
import java.util.function.Function;

public class RemoveButton<T extends AbstractEntity> extends Button {
    public RemoveButton(
        Function<? super T, ? extends Remove<T>> operation,
        Binder<? extends T> binder,
        Class<? extends Component> route
    ) {
        super(
            "Удалить",
            event -> {
                ConfirmDialog.createQuestion()
                    .withCaption("Удаление записи")
                    .withMessage("Данная запись будет безвозвратно удалена. Продолжить?")
                    .withYesButton(
                        () -> {
                            operation.apply(binder.getBean()).perform();
                            new Navigation(route).perform();
                        },
                        ButtonOption.caption("Да"),
                        ButtonOption.focus()
                    ).withNoButton(
                        ButtonOption.caption("Нет")
                    ).open();
            }
        );
        this.addClickShortcut(Key.DELETE);
        this.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
    }
}
