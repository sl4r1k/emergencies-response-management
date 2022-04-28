package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.login.LoginForm;

public class StandardLoginForm extends LoginForm {
    public StandardLoginForm() {
        this.setAction("login");
        this.setForgotPasswordButtonVisible(false);
        this.setI18n(new RussianLoginI18n());
        this.getElement().setAttribute("no-autofocus", "");
    }
}
