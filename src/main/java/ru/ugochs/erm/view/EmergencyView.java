package ru.ugochs.erm.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAll;
import ru.ugochs.erm.view.component.*;

@PageTitle("Происшествия")
@Route("emergencies")
public class EmergencyView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final Grid<Emergency> emergencies;

    public EmergencyView(Db db) {
        this.db = db;
        this.emergencies = new StandardGrid<>(
            new GridColumns<>(
                new GridColumn<>(
                    Emergency::getId,
                    "№"
                ),
                new GridColumn<>(
                    emergency -> emergency.getHappened().toLocalDate(),
                    "Дата"
                ),
                new GridColumn<>(
                    emergency -> emergency.getHappened().toLocalTime(),
                    "Время"
                ),
                new GridColumn<>(
                    emergency -> emergency.getReporter().getName(),
                    "Заявитель"
                ),
                new GridColumn<>(
                    emergency -> emergency.getIndex().getName(),
                    "Индекс"
                ),
                new GridColumn<>(
                    emergency -> emergency.getStreet().getDistrict().getName(),
                    "Район"
                ),
                new GridColumn<>(
                    emergency -> emergency.getStreet().getName(),
                    "Улица"
                ),
                new GridColumn<>(
                    emergency -> emergency.getServices().size(),
                    "СЭР"
                ),
                new GridColumn<>(
                    Emergency::getDead,
                    "Погибло"
                ),
                new GridColumn<>(
                    Emergency::getInjured,
                    "Пострадало"
                ),
                new GridColumn<>(
                    Emergency::getRescued,
                    "Спасено"
                ),
                new GridColumn<>(
                    Emergency::getIsCompleted,
                    "Завершено"
                )
            ),
            clickEvent -> new NavigationByEntity(
                clickEvent.getItem(),
                null
            ).perform()
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Происшествия"),
                new Button(
                    "Добавить",
                    event -> new Navigation(
                        null
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
