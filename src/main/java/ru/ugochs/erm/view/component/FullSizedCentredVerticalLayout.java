package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

public class FullSizedCentredVerticalLayout extends FullSizedVerticalLayout {
    public FullSizedCentredVerticalLayout(Component... components) {
        super(components);
        this.setDefaultHorizontalComponentAlignment(
            FlexComponent.Alignment.CENTER
        );
        this.setJustifyContentMode(
            FlexComponent.JustifyContentMode.CENTER
        );
    }
}
