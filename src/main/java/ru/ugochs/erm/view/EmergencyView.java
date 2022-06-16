package ru.ugochs.erm.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.view.util.PeriodDialog;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.form.add.AddEmergencyForm;
import ru.ugochs.erm.view.form.edit.EditEmergencyForm;
import ru.ugochs.erm.view.util.LocalDateAsRussianString;
import javax.annotation.security.RolesAllowed;
import java.util.Map;

@RolesAllowed({"ADMIN", "USER"})
@PageTitle("Происшествия")
@Route(value = "emergencies", layout = NavigationMenu.class)
public class EmergencyView extends FullSizedVerticalLayout implements BeforeEnterObserver {
    private final Db db;
    private final EmergenciesFilterCriteriaDetails details;
    private final Grid<Emergency> emergencies;

    public EmergencyView(Db db) {
        this.db = db;
        this.emergencies = new StandardPaginatedGrid<>(
            new GridColumns<>(
                new ValueGridColumn<>(
                    Emergency::getId,
                    "№"
                ),
                new ValueGridColumn<>(
                    emergency -> new LocalDateAsRussianString(
                        emergency.getHappened().toLocalDate()
                    ).toString(),
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
                    "Привлечено"
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
        this.details = new EmergenciesFilterCriteriaDetails(
            this.emergencies,
            this.db
        );
        this.add(
            new StandardHorizontalLayout(
                new H2("Происшествия"),
                new Button(
                    "Добавить",
                    event -> new Navigation(
                        AddEmergencyForm.class
                    ).perform()
                ),
                new Button(
                    "Донесение",
                    new LineAwesomeIcon("la la-clipboard-list"),
                    event -> new PeriodDialog(
                        (from, to) -> UI.getCurrent()
                            .navigate(
                                "report",
                                QueryParameters.simple(
                                    Map.of(
                                        "from", from.toString(),
                                        "to", to.toString()
                                    )
                                )
                            )
                    ).open()
                ),
                new Button(
                    "Информация",
                    new LineAwesomeIcon("la la-clipboard-list"),
                    event -> new PeriodDialog(
                        (from, to) -> UI.getCurrent()
                            .navigate(
                                "information",
                                QueryParameters.simple(
                                    Map.of(
                                        "from", from.toString(),
                                        "to", to.toString()
                                    )
                                )
                            )
                    ).open()
                )
            ),
            this.details,
            this.emergencies
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.details.filter(event.getLocation().getQueryParameters());
    }
}
