package ru.ugochs.erm.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.add.AddEmergencyForm;
import ru.ugochs.erm.view.form.edit.EditEmergencyForm;

@PageTitle("Происшествия")
@Route(value = "emergencies", layout = MainLayout.class)
public class EmergencyView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final Grid<Emergency> emergencies;

    public EmergencyView(Db db) {
        this.db = db;
        this.emergencies = new StandardGrid<>(
            new GridColumns<>(
                new ValueGridColumn<>(
                    Emergency::getId,
                    "№"
                ),
                new ValueGridColumn<>(
                    emergency -> emergency.getHappened().toLocalDate(),
                    "Дата"
                ),
                new ValueGridColumn<>(
                    emergency -> emergency.getHappened().toLocalTime(),
                    "Время"
                ),
                new ValueGridColumn<>(
                    emergency -> emergency.getReporter().getName(),
                    "Заявитель"
                ),
                new ValueGridColumn<>(
                    emergency -> emergency.getIndex().getName(),
                    "Индекс"
                ),
                new ValueGridColumn<>(
                    emergency -> emergency.getStreet().getDistrict().getName(),
                    "Район"
                ),
                new ValueGridColumn<>(
                    emergency -> emergency.getStreet().getName(),
                    "Улица"
                ),
                new ValueGridColumn<>(
                    emergency -> emergency.getServices().size(),
                    "СЭР"
                ),
                new ValueGridColumn<>(
                    Emergency::getDead,
                    "Погибло"
                ),
                new ValueGridColumn<>(
                    Emergency::getInjured,
                    "Пострадало"
                ),
                new ValueGridColumn<>(
                    Emergency::getRescued,
                    "Спасено"
                ),
                new RenderGridColumn<>(
                    new ComponentRenderer<>(
                        emergency -> new YesNoBadge(
                            emergency.getIsCompleted()
                        )
                    ),
                    "Завершено"
                )
            ),
            clickEvent -> new NavigationByEntity(
                clickEvent.getItem(),
                EditEmergencyForm.class
            ).perform()
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Происшествия"),
                new Button(
                    "Добавить",
                    event -> new Navigation(
                        AddEmergencyForm.class
                    ).perform()
                )
            ),
            this.emergencies
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.emergencies.setItems(
            new GetAll<>(
                this.db,
                Emergency.class
            ).perform()
        );
    }
}
