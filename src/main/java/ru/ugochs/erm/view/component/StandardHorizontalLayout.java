package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class StandardHorizontalLayout extends HorizontalLayout {
    public StandardHorizontalLayout(Component... components) {
        super(components);
        this.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        this.setMargin(true);
    }
}
