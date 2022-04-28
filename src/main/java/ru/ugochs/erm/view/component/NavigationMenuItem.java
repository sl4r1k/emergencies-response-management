package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import ru.ugochs.erm.view.util.PageTitleAnnotationValue;

public class NavigationMenuItem extends ListItem {
    private final Class<? extends Component> view;

    public NavigationMenuItem(
        Class<? extends Component> view,
        String iconClass
    ) {
        this.view = view;
        this.add(
            new NavigationMenuRouterLink(
                this.view,
                new LineAwesomeIcon(iconClass),
                new NavigationMenuRouterLinkTextSpan(
                    new PageTitleAnnotationValue(
                        this.view
                    ).value()
                )
            )
        );
    }

    public boolean isAccessible() {
        return new AccessAnnotationChecker().hasAccess(this.view);
    }
}
