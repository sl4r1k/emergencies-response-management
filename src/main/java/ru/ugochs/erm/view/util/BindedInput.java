package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;

public interface BindedInput<C extends Component & HasValue<?, T>, T> {
    C bind();
}
