package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.function.ValueProvider;
import ru.ugochs.erm.entity.AbstractEntity;

public class StandardGrid<T extends AbstractEntity> extends Grid<T> {
    public StandardGrid(ValueProvider<T, ?> value, ComponentEventListener<ItemClickEvent<T>> clickEvent) {
        this.addColumn(value);
        this.addItemClickListener(clickEvent);
        this.sort(GridSortOrder.asc(this.getColumns().get(0)).build());
        this.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        this.getStyle().set("font-size", "140%");
    }
}
