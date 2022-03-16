package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.UI;

public class BackInHistory {
    public void perform() {
        UI.getCurrent().getPage().getHistory().back();
    }
}
