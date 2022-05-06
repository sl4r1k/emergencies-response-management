package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import org.apache.commons.lang3.function.TriFunction;
import ru.ugochs.erm.entity.AbstractEntity;
import javax.persistence.criteria.*;
import java.util.Optional;
import java.util.function.Function;

public class FilterableInput<E extends AbstractEntity,
                             T1 extends Comparable<? super T1>,
                             C extends Component & HasValue<?, T1>,
                             T2> {
    private final C input;
    private final TriFunction<CriteriaBuilder, Path<T2>, T1, Predicate> condition;
    private final Function<Root<E>, Path<T2>> target;
    private final String title;

    public FilterableInput(
        C input,
        TriFunction<CriteriaBuilder, Path<T2>, T1, Predicate> condition,
        Function<Root<E>, Path<T2>> target,
        String title
    ) {
        this.input = input;
        this.condition = condition;
        this.target = target;
        this.title = title;
    }

    public Optional<Predicate> predicate(CriteriaBuilder criteria, Root<E> entity) {
        if (this.value() instanceof Boolean && !((Boolean) this.value())) {
            return Optional.empty();
        }
        return this.input.getOptionalValue().map(
            value -> this.condition.apply(
                criteria,
                this.target.apply(entity),
                value
            )
        );
    }

    public C component() {
        return this.input;
    }

    public T1 value() {
        return this.input.getValue();
    }

    public void setValue(T1 value) {
        this.input.setValue(value);
    }

    public boolean isNotEmpty() {
        return !this.input.isEmpty();
    }

    public String title() {
        return this.title;
    }
}
