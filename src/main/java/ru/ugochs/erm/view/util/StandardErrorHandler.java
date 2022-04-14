package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;
import ru.ugochs.erm.view.component.ErrorDialog;
import java.util.Objects;

public class StandardErrorHandler implements ErrorHandler {
    @Override
    public void error(ErrorEvent event) {
        if (Objects.nonNull(UI.getCurrent())) {
            UI.getCurrent().access(() -> {
                new ErrorDialog(
                    new UnwrappedErrorMessage(
                        event.getThrowable()
                    ).text()
                );
            });
        }
    }
}
