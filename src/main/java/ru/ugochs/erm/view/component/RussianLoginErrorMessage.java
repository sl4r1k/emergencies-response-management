package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.login.LoginI18n;

public class RussianLoginErrorMessage extends LoginI18n.ErrorMessage {
    public RussianLoginErrorMessage() {
        this.setTitle("Неверный логин или пароль");
        this.setMessage("Убедитесь, что вы ввели правильный логин и пароль, и повторите попытку.");
    }
}
