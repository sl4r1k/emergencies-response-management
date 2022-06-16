package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import org.apache.commons.lang3.ArrayUtils;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.view.util.QueryParametersAsSimpleMap;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.EmergencyView;
import ru.ugochs.erm.view.util.EmergencyFilterableInputs;

public class EmergenciesFilterCriteriaDetails extends Accordion {
    private final Grid<Emergency> grid;
    private final Db db;
    private  Button apply;
    private  Button clear;
    private EmergencyFilterableInputs filter;
    private HorizontalLayout layout1;
    private HorizontalLayout layout2;

    public EmergenciesFilterCriteriaDetails(Grid<Emergency> grid, Db db) {
        this.grid = grid;
        this.db = db;
        this.filter = new EmergencyFilterableInputs(this.db);
        this.apply = new Button(
            "Применить",
            event -> UI.getCurrent().navigate(
                "emergencies",
                this.filter.parameters()
            )
        );
        this.clear = new Button(
            "Очистить",
            event -> UI.getCurrent().navigate(EmergencyView.class)
        );
        this.layout1 = new HorizontalLayout();
        this.layout2 = new HorizontalLayout();
        AccordionPanel panel = new AccordionPanel(
            "Фильтр",
            new VerticalLayout(
                this.layout1,
                this.layout2,
                new StandardHorizontalLayout(
                    this.apply,
                    this.clear
                )
            )
        );
        panel.getSummary()
            .getElement()
            .getStyle()
            .set("font-size", "150%");
        this.add(panel);
    }

    public void filter(QueryParameters parameters) {
        if (parameters.getParameters().isEmpty()) {
            this.filter = new EmergencyFilterableInputs(this.db);
            this.grid.setItems(
                new GetAll<>(
                    this.db,
                    Emergency.class
                ).perform()
            );
            this.close();
        } else {
            this.filter = new EmergencyFilterableInputs(
                new QueryParametersAsSimpleMap(parameters),
                this.db
            );
            this.grid.setItems(
                this.filter.criteria()
                    .query()
                    .getResultList()
            );
            this.open(0);
        }
        this.layout1.removeAll();
        this.layout1.add(
            ArrayUtils.subarray(
                this.filter.components(), 0, 8
            )
        );
        this.layout2.removeAll();
        this.layout2.add(
            ArrayUtils.subarray(
                this.filter.components(), 8, 14
            )
        );
    }
}
