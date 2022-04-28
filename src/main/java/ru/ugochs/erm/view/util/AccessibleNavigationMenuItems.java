package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.UnorderedList;
import ru.ugochs.erm.view.component.NavigationMenuItem;
import java.util.Arrays;

public class AccessibleNavigationMenuItems extends UnorderedList {
    public AccessibleNavigationMenuItems(NavigationMenuItem... items) {
        this.addClassNames(
            "flex",
            "list-none",
            "m-0",
            "p-0"
        );
        this.add(
            Arrays.stream(items)
                .filter(NavigationMenuItem::isAccessible)
                .toArray(Component[]::new)
        );
    }
}
