package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import ru.ugochs.erm.view.Navigation;

public class CancelButton extends Button {
    public CancelButton(Class<? extends Component> route) {
        super("Отмена", event -> new Navigation(route).perform());
        this.addClickShortcut(Key.ESCAPE);
    }
}
