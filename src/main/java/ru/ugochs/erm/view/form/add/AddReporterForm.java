package ru.ugochs.erm.view.form.add;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import ru.ugochs.erm.entity.Reporter;
import ru.ugochs.erm.service.crud.AddReporter;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.AddButton;
import ru.ugochs.erm.view.component.CancelButton;
import ru.ugochs.erm.view.form.ReporterForm;

@Route("reporters/add")
public class AddReporterForm extends ReporterForm {
    public AddReporterForm(Db db) {
        super(db);
        this.binder.setBean(new Reporter());
        this.add(
            new HorizontalLayout(
                new AddButton<>(
                    reporter -> new AddReporter(reporter, this.db),
                    this.binder,
                    ReporterView.class
                ),
                new CancelButton(ReporterView.class)
            )
        );
    }
}
