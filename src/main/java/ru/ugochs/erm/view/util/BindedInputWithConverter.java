package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.function.ValueProvider;
import ru.ugochs.erm.entity.AbstractEntity;

public class BindedInputWithConverter<C extends Component & HasValue<?, P>, E extends AbstractEntity, T, P>
    implements BindedInput<C, P> {
    private final C component;
    private final Binder<E> binder;
    private final Converter<P, T> converter;
    private final ValueProvider<E, T> getter;
    private final Setter<E, T> setter;
    private final boolean required;

    public BindedInputWithConverter(
        C component,
        Binder<E> binder,
        Converter<P, T> converter,
        ValueProvider<E, T> getter,
        Setter<E, T> setter,
        boolean required
    ) {
        this.component = component;
        this.binder = binder;
        this.converter = converter;
        this.getter = getter;
        this.setter = setter;
        this.required = required;
    }

    @Override
    public C bind() {
        Binder.BindingBuilder<E, T> binding = this.binder.forField(this.component).withConverter(this.converter);
        if (this.required) {
            binding = binding.asRequired("Заполните поле");
        }
        return (C) binding.bind(this.getter, this.setter).getField();
    }
}
