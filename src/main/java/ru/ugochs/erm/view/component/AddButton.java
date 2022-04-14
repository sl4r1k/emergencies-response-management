package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.data.binder.Binder;
import ru.ugochs.erm.entity.AbstractEntity;
import ru.ugochs.erm.service.crud.Add;
import ru.ugochs.erm.view.Navigation;
import java.util.function.Function;

public class AddButton<T extends AbstractEntity> extends Button {
    public AddButton(
        Function<? super T, ? extends Add<T>> operation,
        Binder<? extends T> binder,
        Class<? extends Component> route
    ) {
        super(
            "Добавить",
            event -> {
                if (!binder.validate().isOk()) {
                    return;
                }
                operation.apply(binder.getBean()).perform();
                new Navigation(route).perform();
            }
        );
        this.addClickShortcut(Key.ENTER);
        this.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }
}
