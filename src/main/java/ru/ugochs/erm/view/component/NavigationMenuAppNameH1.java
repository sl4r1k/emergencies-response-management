package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.html.H1;

public class NavigationMenuAppNameH1 extends H1 {
    public NavigationMenuAppNameH1(String name) {
        super(name);
        this.addClassNames("my-0", "me-auto", "text-l");
    }
}
