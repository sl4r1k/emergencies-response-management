package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.*;
import ru.ugochs.erm.entity.AbstractEntity;

public class StandardGrid<T extends AbstractEntity> extends Grid<T> {
    public StandardGrid(GridColumns<T> columns, ComponentEventListener<ItemClickEvent<T>> clickEvent) {
        columns.setToGrid(this);
        this.addItemClickListener(clickEvent);
        this.sort(GridSortOrder.asc(this.getColumns().get(0)).build());
        this.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_WRAP_CELL_CONTENT);
        this.getStyle().set("font-size", "140%");
    }
}
