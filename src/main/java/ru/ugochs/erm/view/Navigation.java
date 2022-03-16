package ru.ugochs.erm.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;

public class Navigation {
    protected final Class<? extends Component> componentType;

    public Navigation(Class<? extends Component> componentType) {
        this.componentType = componentType;
    }

    public void perform() {
        UI.getCurrent().navigate(this.componentType);
    }
}
