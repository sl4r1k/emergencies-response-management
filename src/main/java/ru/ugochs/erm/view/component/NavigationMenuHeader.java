package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Header;

public class NavigationMenuHeader extends Header {
    public NavigationMenuHeader(Component... components) {
        super(components);
        this.addClassNames(
            "bg-base",
            "border-b",
            "border-contrast-10",
            "box-border",
            "flex",
            "flex-col",
            "w-full"
        );
    }
}
