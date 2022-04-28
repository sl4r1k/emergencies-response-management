package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.login.LoginI18n;

public class RussianLoginI18n extends LoginI18n {
    public RussianLoginI18n() {
        this.setForm(new RussianLoginForm());
        this.setErrorMessage(new RussianLoginErrorMessage());
    }
}
