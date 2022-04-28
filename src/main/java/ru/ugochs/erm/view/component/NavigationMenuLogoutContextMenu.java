package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import ru.ugochs.erm.configuration.security.Logout;

public class NavigationMenuLogoutContextMenu extends ContextMenu {
    public NavigationMenuLogoutContextMenu(Component target) {
        super(target);
        this.setOpenOnClick(true);
        this.addItem(
            "Выйти",
            event -> new Logout().perform()
        );
    }
}
