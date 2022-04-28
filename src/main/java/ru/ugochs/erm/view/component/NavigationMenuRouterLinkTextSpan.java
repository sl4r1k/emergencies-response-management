package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.html.Span;

public class NavigationMenuRouterLinkTextSpan extends Span {
    public NavigationMenuRouterLinkTextSpan(String text) {
        super(text);
        this.addClassNames("font-medium", "text-s", "whitespace-nowrap");
    }
}
