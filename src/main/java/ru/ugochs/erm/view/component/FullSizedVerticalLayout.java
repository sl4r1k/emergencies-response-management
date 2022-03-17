package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class FullSizedVerticalLayout extends VerticalLayout {
    public FullSizedVerticalLayout(Component... components) {
        super(components);
        this.setSizeFull();
    }
}
