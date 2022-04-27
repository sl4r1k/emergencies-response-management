package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import ru.ugochs.erm.entity.AbstractEntity;

public class StandardBindedInput<C extends Component & HasValue<?, T>, E extends AbstractEntity, T>
    implements BindedInput<C, T> {
    private final C component;
    private final Binder<E> binder;
    private final ValueProvider<E, T> getter;
    private final Setter<E, T> setter;
    private final boolean required;

    public StandardBindedInput(
        C component,
        Binder<E> binder,
        ValueProvider<E, T> getter,
        Setter<E, T> setter,
        boolean required
    ) {
        this.component = component;
        this.binder = binder;
        this.getter = getter;
        this.setter = setter;
        this.required = required;
    }

    @Override
    public C bind() {
        Binder.BindingBuilder<E, T> binding = this.binder.forField(this.component);
        if (this.required) {
            binding.asRequired("Заполните поле");
        }
        return (C) binding.bind(
            this.getter,
            this.setter
        ).getField();
    }
}
