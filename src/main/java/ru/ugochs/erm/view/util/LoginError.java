package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.Location;

public class LoginError {
    private final LoginForm login;
    private final Location location;

    public LoginError(LoginForm login, Location location) {
        this.login = login;
        this.location = location;
    }

    public void showErrorMessageIfContains() {
        if (
            this.location
                .getQueryParameters()
                .getParameters()
                .containsKey("error")
        ) {
            this.login.setError(true);
        }
    }
}
