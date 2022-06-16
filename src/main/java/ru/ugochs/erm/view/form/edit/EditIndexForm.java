package ru.ugochs.erm.view.form.edit;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Index;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.*;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.IndexForm;
import ru.ugochs.erm.view.component.NavigationMenu;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@Route(value = "indexes/:id/edit", layout = NavigationMenu.class)
public class EditIndexForm extends IndexForm implements BeforeEnterObserver {
    public EditIndexForm(Db db) {
        super(db);
        this.add(
            new HorizontalLayout(
                new EditButton<>(
                    index -> new EditIndex(index, this.db),
                    this.binder,
                    IndexView.class
                ),
                new RemoveButton<>(
                    index -> new RemoveIndex(index, this.db),
                    this.binder,
                    IndexView.class
                ),
                new CancelButton(IndexView.class)
            )
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        new GetById<>(
            new IdFromRoute(event.getRouteParameters()).value(),
            this.db,
            Index.class
        ).perform().ifPresentOrElse(
            index -> {
                this.binder.setBean(index);
                String parentName = this.parentIndex.getValue();
                this.parentIndex.setItems(
                    new GetAllIndexNamesExceptLevel3AndOneElse(index, this.db).perform()
                );
                this.parentIndex.setValue(parentName);
            },
            () -> event.rerouteToError(
                NotFoundException.class,
                "Нет такого индекса"
            )
        );
    }
}
