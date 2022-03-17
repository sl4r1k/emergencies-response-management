package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CentredVerticalLayout extends VerticalLayout {
    public CentredVerticalLayout(Component... components) {
        super(components);
        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}
