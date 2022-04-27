package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.function.ValueProvider;

public class ValueGridColumn<T> implements GridColumn<T> {
    private final ValueProvider<T, ?> value;
    private final String header;

    public ValueGridColumn(ValueProvider<T, ?> value) {
        this(value, "");
    }

    public ValueGridColumn(ValueProvider<T, ?> value, String header) {
        this.value = value;
        this.header = header;
    }

    public void setToGrid(Grid<T> grid) {
        grid.addColumn(this.value)
            .setHeader(this.header)
            .setResizable(true);
    }
}
