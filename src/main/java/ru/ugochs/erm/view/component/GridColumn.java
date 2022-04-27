package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.grid.Grid;

public interface GridColumn<T> {
    void setToGrid(Grid<T> grid);
}
