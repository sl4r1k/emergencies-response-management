package ru.ugochs.erm.view.form.add;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import ru.ugochs.erm.entity.Street;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.StreetView;
import ru.ugochs.erm.view.component.AddButton;
import ru.ugochs.erm.view.component.CancelButton;
import ru.ugochs.erm.view.form.StreetForm;

@Route("streets/add")
public class AddStreetForm extends StreetForm {
    public AddStreetForm(Db db) {
        super(db);
        this.binder.setBean(new Street());
        this.district.setItems(
            new GetAllDistrictNames(this.db).perform()
        );
        this.add(
            new HorizontalLayout(
                new AddButton<>(
                    street -> new AddStreet(street, this.db),
                    this.binder,
                    StreetView.class
                ),
                new CancelButton(StreetView.class)
            )
        );
    }
}
