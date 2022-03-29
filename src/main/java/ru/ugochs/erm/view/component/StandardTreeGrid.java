package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.function.ValueProvider;
import ru.ugochs.erm.entity.AbstractEntity;

public class StandardTreeGrid<T extends AbstractEntity> extends TreeGrid<T> {
    public StandardTreeGrid(ValueProvider<T, ?> value, ComponentEventListener<ItemDoubleClickEvent<T>> clickEvent) {
        this.addHierarchyColumn(value);
        this.addItemDoubleClickListener(clickEvent);
        this.sort(GridSortOrder.asc(this.getColumns().get(0)).build());
        this.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        this.getStyle().set("font-size", "140%");
    }
}
