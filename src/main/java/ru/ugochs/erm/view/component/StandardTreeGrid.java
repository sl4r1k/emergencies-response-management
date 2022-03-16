package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.ItemDoubleClickEvent;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.function.ValueProvider;
import ru.ugochs.erm.entity.AbstractEntity;

public class StandardTreeGrid<T extends AbstractEntity> extends TreeGrid<T> {
    public StandardTreeGrid(ValueProvider<T, ?> value, ComponentEventListener<ItemDoubleClickEvent<T>> clickEvent) {
        this.addHierarchyColumn(value);
        this.addItemDoubleClickListener(clickEvent);
        this.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        this.getStyle().set("font-size", "140%");
    }
}
