package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.data.binder.Binder;
import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.crud.Remove;
import java.util.function.Function;

public class RemoveButton<T extends AbstractEntity> extends Button {
    public RemoveButton(
        Function<? super T, ? extends Remove<T>> operation,
        Binder<? extends T> binder
    ) {
        super(
            "Удалить",
            event -> {
                operation.apply(binder.getBean()).perform();
                new BackInHistory().perform();
            }
        );
        this.addClickShortcut(Key.DELETE);
        this.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
    }
}
