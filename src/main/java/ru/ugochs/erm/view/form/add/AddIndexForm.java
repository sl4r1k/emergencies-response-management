package ru.ugochs.erm.view.form.add;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import ru.ugochs.erm.entity.Index;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.IndexView;
import ru.ugochs.erm.view.component.AddButton;
import ru.ugochs.erm.view.component.CancelButton;
import ru.ugochs.erm.view.form.IndexForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed({"ADMIN", "USER"})
@Route("indexes/add")
public class AddIndexForm extends IndexForm {
    public AddIndexForm(Db db) {
        super(db);
        this.binder.setBean(new Index());
        this.parentIndex.setItems(new GetAllIndexNamesExceptLevel3(this.db).perform());
        this.add(
            new HorizontalLayout(
                new AddButton<>(
                    index -> new AddIndex(index, this.db),
                    this.binder,
                    IndexView.class
                ),
                new CancelButton(IndexView.class)
            )
        );
    }
}
