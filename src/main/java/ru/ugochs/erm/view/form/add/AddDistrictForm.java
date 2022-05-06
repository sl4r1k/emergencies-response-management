package ru.ugochs.erm.view.form.add;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import ru.ugochs.erm.entity.District;
import ru.ugochs.erm.service.crud.AddDistrict;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.DistrictView;
import ru.ugochs.erm.view.component.AddButton;
import ru.ugochs.erm.view.component.CancelButton;
import ru.ugochs.erm.view.form.DistrictForm;
import javax.annotation.security.RolesAllowed;

@RolesAllowed({"ADMIN", "USER"})
@Route("districts/add")
public class AddDistrictForm extends DistrictForm {
    public AddDistrictForm(Db db) {
        super(db);
        this.binder.setBean(new District());
        this.add(
            new HorizontalLayout(
                new AddButton<>(
                    district -> new AddDistrict(district, this.db),
                    this.binder,
                    DistrictView.class
                ),
                new CancelButton(DistrictView.class)
            )
        );
    }
}
