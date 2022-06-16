package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.*;
import org.vaadin.klaudeta.PaginatedGrid;
import ru.ugochs.erm.entity.AbstractEntity;

public class StandardPaginatedGrid<T extends AbstractEntity> extends PaginatedGrid<T> {
    public StandardPaginatedGrid(GridColumns<T> columns, ComponentEventListener<ItemClickEvent<T>> clickEvent) {
        columns.setToGrid(this);
        this.addItemClickListener(clickEvent);
        this.sort(
            GridSortOrder.asc(
                this.getColumns().get(0)
            ).build()
        );
        this.addThemeVariants(
            GridVariant.LUMO_NO_BORDER,
            GridVariant.LUMO_WRAP_CELL_CONTENT
        );
        this.setPageSize(20);
        this.setPaginatorSize(5);
        this.setPaginatorTexts("Страница", "из");
        this.getStyle().set("font-size", "140%");
        this.setSizeFull();
    }
}
