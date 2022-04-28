package ru.ugochs.erm.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Reporter;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.add.AddReporterForm;
import ru.ugochs.erm.view.form.edit.EditReporterForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@PageTitle("Заявители")
@Route(value = "reporters", layout = NavigationMenu.class)
public class ReporterView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final Grid<Reporter> reporters;

    public ReporterView(Db db) {
        this.db = db;
        this.reporters = new StandardGrid<>(
            new GridColumns<>(
                new ValueGridColumn<>(
                    Reporter::getName
                )
            ),
            clickEvent -> new NavigationByEntity(
                clickEvent.getItem(),
                EditReporterForm.class
            ).perform()
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Заявители"),
                new Button(
                    "Добавить",
                    event -> new Navigation(
                        AddReporterForm.class
                    ).perform()
                )
            ),
            this.reporters
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.reporters.setItems(
            new GetAll<>(
                this.db,
                Reporter.class
            ).perform()
        );
    }
}
