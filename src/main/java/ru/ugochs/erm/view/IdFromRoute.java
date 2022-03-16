package ru.ugochs.erm.view;

import com.vaadin.flow.router.RouteParameters;

public class IdFromRoute {
    private final RouteParameters parameters;

    public IdFromRoute(RouteParameters parameters) {
        this.parameters = parameters;
    }

    public Long value() {
        return Long.parseLong(this.parameters.get("id").get());
    }
}
