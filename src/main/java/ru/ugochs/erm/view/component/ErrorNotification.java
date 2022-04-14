package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class ErrorNotification extends Notification {
    public ErrorNotification(String text) {
        super(text);
        this.setDuration(3000);
        this.setPosition(Position.MIDDLE);
        this.addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
