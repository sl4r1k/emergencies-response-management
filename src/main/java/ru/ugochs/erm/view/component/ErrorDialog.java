package ru.ugochs.erm.view.component;

import org.claspina.confirmdialog.ButtonOption;
import org.claspina.confirmdialog.ConfirmDialog;

public class ErrorDialog implements Dialog {
    private final ConfirmDialog dialog;

    public ErrorDialog(String message) {
        this.dialog = ConfirmDialog.createError()
            .withCaption("Ошибка")
            .withMessage(message)
            .withOkButton(
                ButtonOption.caption("Понятно"),
                ButtonOption.focus()
            );
    }

    @Override
    public void show() {
        this.dialog.open();
    }
}
