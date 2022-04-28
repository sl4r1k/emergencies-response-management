package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.UnorderedList;

public class NavigationMenuNav extends Nav {
    public NavigationMenuNav(UnorderedList list) {
        this.addClassNames(
            "flex",
            "gap-s",
            "overflow-auto",
            "px-m"
        );
        this.add(list);
    }
}
