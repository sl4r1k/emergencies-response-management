package ru.ugochs.erm;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.context.annotation.Configuration;
import ru.ugochs.erm.view.util.StandardErrorHandler;

@Configuration
public class ErrorInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource()
            .addSessionInitListener(
                e -> e.getSession()
                    .setErrorHandler(new StandardErrorHandler())
            );
    }
}
