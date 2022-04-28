package ru.ugochs.erm.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Index;
import ru.ugochs.erm.service.FilterIndexesByRelativeName;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAllIndexesByLevel1;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.add.AddIndexForm;
import ru.ugochs.erm.view.form.edit.EditIndexForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@PageTitle("Индексы")
@Route(value = "indexes", layout = NavigationMenu.class)
public class IndexView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final TreeGrid<Index> indexes;

    public IndexView(Db db) {
        this.db = db;
        this.indexes = new StandardTreeGrid<>(
            Index::getName,
            clickEvent -> new NavigationByEntity(clickEvent.getItem(), EditIndexForm.class).perform()
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Индексы"),
                new FilterTextField(event ->
                    this.indexes.setItems(
                        new FilterIndexesByRelativeName(event.getValue(), this.db).perform(),
                        Index::getChildren
                    )
                ),
                new Button("Добавить", event -> new Navigation(AddIndexForm.class).perform())
            ),
            this.indexes
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.indexes.setItems(
            new GetAllIndexesByLevel1(this.db).perform(),
            Index::getChildren
        );
    }
}
