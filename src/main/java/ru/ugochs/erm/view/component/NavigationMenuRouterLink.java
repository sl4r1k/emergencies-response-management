package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.RouterLink;

public class NavigationMenuRouterLink extends RouterLink {
    public NavigationMenuRouterLink(
        Class<? extends Component> view,
        Span icon,
        Span text
    ) {
        this.addClassNames(
            "flex",
            "h-m",
            "items-center",
            "px-s",
            "relative",
            "text-secondary"
        );
        this.setRoute(view);
        this.add(icon, text);
    }
}
