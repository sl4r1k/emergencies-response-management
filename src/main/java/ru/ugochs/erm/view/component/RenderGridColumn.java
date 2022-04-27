package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.Renderer;

public class RenderGridColumn<T> implements GridColumn<T> {
    private final Renderer<T> renderer;
    private final String header;

    public RenderGridColumn(Renderer<T> renderer) {
        this(renderer, "");
    }

    public RenderGridColumn(Renderer<T> renderer, String header) {
        this.renderer = renderer;
        this.header = header;
    }

    public void setToGrid(Grid<T> grid) {
        grid.addColumn(this.renderer)
            .setHeader(this.header)
            .setResizable(true);
    }
}
