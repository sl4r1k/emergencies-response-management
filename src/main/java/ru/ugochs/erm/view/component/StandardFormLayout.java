package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;

public class StandardFormLayout extends FormLayout {
    public StandardFormLayout(Component... components) {
        super(components);
        this.getStyle().set("margin", "0% 35% 0% 35%");
        this.setResponsiveSteps(new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP));
    }
}
