package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.grid.Grid;
import java.util.List;

public class GridColumns<T> {
    private final List<GridColumn<T>> list;

    public GridColumns(GridColumn<T>... array) {
        this.list = List.of(array);
    }

    public void setToGrid(Grid<T> grid) {
        this.list.forEach(column -> column.setToGrid(grid));
    }
}
