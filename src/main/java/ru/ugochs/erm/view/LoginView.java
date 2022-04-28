package ru.ugochs.erm.view;

import com.vaadin.flow.router.*;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.util.LoginError;

@PageTitle("Вход")
@Route("login")
public class LoginView extends FullSizedCentredVerticalLayout implements BeforeEnterObserver {
    private final StandardLoginForm login = new StandardLoginForm();

    public LoginView() {
        this.add(this.login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        new LoginError(
            this.login,
            event.getLocation()
        ).showErrorMessageIfContains();
    }
}
