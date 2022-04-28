package ru.ugochs.erm.view.form.edit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Reporter;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.*;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.ReporterForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@Route(value = "reporters/:id/edit", layout = MainLayout.class)
public class EditReporterForm extends ReporterForm implements BeforeEnterObserver {
    public EditReporterForm(Db db) {
        super(db);
        this.add(
            new HorizontalLayout(
                new EditButton<>(
                    reporter -> new EditReporter(reporter, this.db),
                    this.binder,
                    ReporterView.class
                ),
                new RemoveButton<>(
                    reporter -> new Remove<>(reporter, this.db),
                    this.binder,
                    ReporterView.class
                ),
                new CancelButton(ReporterView.class)
            )
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        new GetById<>(
            new IdFromRoute(event.getRouteParameters()).value(),
            this.db,
            Reporter.class
        ).perform().ifPresentOrElse(
            this.binder::setBean,
            () -> event.rerouteToError(
                NotFoundException.class,
                "Нет такого индекса"
            )
        );
    }
}
