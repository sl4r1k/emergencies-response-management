package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.PageTitle;

public class PageTitleAnnotationValue {
    private final Class<? extends Component> view;

    public PageTitleAnnotationValue(Class<? extends Component> view) {
        this.view = view;
    }

    public String value() {
        return this.view.getDeclaredAnnotation(
            PageTitle.class
        ).value();
    }
}
