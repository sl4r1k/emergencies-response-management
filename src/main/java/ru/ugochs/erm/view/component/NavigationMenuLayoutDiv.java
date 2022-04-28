package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class NavigationMenuLayoutDiv extends Div {
    public NavigationMenuLayoutDiv(Component... components) {
        this.addClassNames(
            "flex",
            "h-xl",
            "items-center",
            "px-l"
        );
        this.add(components);
    }
}
