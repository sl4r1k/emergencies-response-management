package ru.ugochs.erm.configuration.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class Logout {
    public static final String URL = "/";

    public void perform() {
        UI.getCurrent().getPage().setLocation(Logout.URL);
        new SecurityContextLogoutHandler().logout(
            VaadinServletRequest.getCurrent().getHttpServletRequest(),
            null, null
        );
    }
}
