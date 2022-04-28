package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.login.LoginI18n;

public class RussianLoginForm extends LoginI18n.Form {
    public RussianLoginForm() {
        this.setTitle("Войти");
        this.setUsername("Логин");
        this.setPassword("Пароль");
        this.setSubmit("Войти");
    }
}
