package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.data.binder.Binder;
import org.claspina.confirmdialog.ButtonOption;
import org.claspina.confirmdialog.ConfirmDialog;
import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.crud.Edit;
import ru.ugochs.erm.view.Navigation;
import javax.persistence.EntityExistsException;
import java.util.function.Function;

public class EditButton<T extends AbstractEntity> extends Button {
    public EditButton(
        Function<? super T, ? extends Edit<T>> operation,
        Binder<? extends T> binder,
        Class<? extends Component> route
    ) {
        super(
            "Изменить",
            event -> {
                if (!binder.validate().isOk()) {
                    return;
                }
                try {
                    operation.apply(binder.getBean()).perform();
                } catch (EntityExistsException exception) {
                    ConfirmDialog.createError()
                        .withCaption("Ошибка редактирования")
                        .withMessage("Такая запись уже существует!")
                        .withOkButton(
                            ButtonOption.caption("Понятно"),
                            ButtonOption.focus()
                        ).open();
                }
                new Navigation(route).perform();
            }
        );
        this.addClickShortcut(Key.ENTER);
        this.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }
}
