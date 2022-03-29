package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;

public class CancelButton extends Button {
    public CancelButton() {
        super("Отмена", event -> new BackInHistory().perform());
        this.addClickShortcut(Key.ESCAPE);
    }
}
