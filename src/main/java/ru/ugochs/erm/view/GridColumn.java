package ru.ugochs.erm.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.function.ValueProvider;

public class GridColumn<T> {
    private final ValueProvider<T, ?> value;
    private final String header;

    public GridColumn(ValueProvider<T, ?> value) {
        this(value, "");
    }

    public GridColumn(ValueProvider<T, ?> value, String header) {
        this.value = value;
        this.header = header;
    }

    public void setToGrid(Grid<T> grid) {
        grid.addColumn(this.value).setHeader(this.header);
    }
}
