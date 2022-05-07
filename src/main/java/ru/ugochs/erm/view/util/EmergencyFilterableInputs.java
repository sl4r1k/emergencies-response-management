package ru.ugochs.erm.view.util;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.QueryParameters;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.entity.Service;
import ru.ugochs.erm.service.crud.*;
import ru.ugochs.erm.view.component.RussianDatePicker;
import ru.ugochs.erm.view.component.StandardComboBox;
import ru.ugochs.erm.view.filt.StandardRadioButtonGroup;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class EmergencyFilterableInputs {
    private final List<FilterableInput<Emergency, ?, ? ,?>> inputs;
    private final Db db;

    public EmergencyFilterableInputs(Map<String, String> parameters, Db db) {
        ComboBox<String> street = new StandardComboBox(
            "Улица",
            new GetAllStreetNames(db).perform(),
            parameters.get("streetName")
        );
        ComboBox<String> district = new StandardComboBox(
            "Район",
            new GetAllDistrictNames(db).perform(),
            parameters.get("districtName")
        );
        district.addValueChangeListener(
            event -> street.setItems(
                new GetAllStreetNamesByDistrictName(
                    event.getValue(),
                    db
                ).perform()
            )
        );
        this.inputs = List.of(
            new FilterableInput<>(
                new RussianDatePicker("С", new StringAsLocalDate(parameters.get("startDate")).value()),
                (criteria, target, value) -> criteria.greaterThanOrEqualTo(target.as(LocalDate.class), value),
                entity -> entity.get("happened"),
                "startDate"
            ),
            new FilterableInput<>(
                new TimePicker("⠀", new StringAsLocalTime(parameters.get("startTime")).value()),
                (criteria, target, value) -> criteria.greaterThanOrEqualTo(target.as(LocalTime.class), value),
                entity -> entity.get("happened"),
                "startTime"
            ),
            new FilterableInput<>(
                new RussianDatePicker("По", new StringAsLocalDate(parameters.get("endDate")).value()),
                (criteria, target, value) -> criteria.lessThanOrEqualTo(target.as(LocalDate.class), value),
                entity -> entity.get("happened"),
                "endDate"
            ),
            new FilterableInput<>(
                new TimePicker("⠀", new StringAsLocalTime(parameters.get("endTime")).value()),
                (criteria, target, value) -> criteria.lessThanOrEqualTo(target.as(LocalTime.class), value),
                entity -> entity.get("happened"),
                "endTime"
            ),
            new FilterableInput<>(
                new StandardComboBox(
                    "Заявитель",
                    new GetAllReporterNames(db).perform(),
                    parameters.get("reporterName")
                ),
                CriteriaBuilder::equal,
                entity -> entity.get("reporter").get("name"),
                "reporterName"
            ),
            new FilterableInput<>(
                new StandardComboBox(
                    "Индекс",
                    new GetAllIndexNamesByLevel3(db).perform(),
                    parameters.get("indexName")
                ),
                CriteriaBuilder::equal,
                entity -> entity.get("index").get("name"),
                "indexName"
            ),
            new FilterableInput<>(
                district,
                CriteriaBuilder::equal,
                entity -> entity.get("street").get("district").get("name"),
                "districtName"
            ),
            new FilterableInput<>(
                street,
                CriteriaBuilder::equal,
                entity -> entity.get("street").get("name"),
                "streetName"
            ),
            new FilterableInput<>(
                new Checkbox("Привлечения", Boolean.parseBoolean(parameters.get("attractions"))),
                (criteria, target, value) -> criteria.isNotEmpty(target),
                entity -> entity.<Collection<Service>>get("services"),
                "attractions"
            ),
            new FilterableInput<>(
                new Checkbox("Погибшие", Boolean.parseBoolean(parameters.get("dead"))),
                (criteria, target, value) -> criteria.greaterThan(target, 0),
                entity -> entity.<Integer>get("dead"),
                "dead"
            ),
            new FilterableInput<>(
                new Checkbox("Пострадавшие", Boolean.parseBoolean(parameters.get("injured"))),
                (criteria, target, value) -> criteria.greaterThan(target, 0),
                entity -> entity.<Integer>get("injured"),
                "injured"
            ),
            new FilterableInput<>(
                new Checkbox("Спасенные", Boolean.parseBoolean(parameters.get("rescued"))),
                (criteria, target, value) -> criteria.greaterThan(target, 0),
                entity -> entity.<Integer>get("rescued"),
                "rescued"
            ),
            new FilterableInput<>(
                new StandardRadioButtonGroup(
                    "",
                    List.of("Завершено", "Не завершено"),
                    parameters.get("completed")
                ),
                (criteria, target, value) -> criteria.equal(criteria.literal(value.equals("Завершено")), target),
                entity -> entity.get("isCompleted"),
                "completed"
            )
        );
        this.db = db;
    }

    public EmergencyFilterableInputs(Db db) {
        ComboBox<String> street = new StandardComboBox(
            "Улица",
            new GetAllStreetNames(db).perform()
        );
        ComboBox<String> district = new StandardComboBox(             
            "Район",
            new GetAllDistrictNames(db).perform()
        );
        district.addValueChangeListener(
            event -> street.setItems(
                new GetAllStreetNamesByDistrictName(
                    event.getValue(), 
                    db
                ).perform()
            )
        );
        this.inputs = List.of(
            new FilterableInput<>(
                new RussianDatePicker("С"),
                (criteria, target, value) -> criteria.greaterThanOrEqualTo(target.as(LocalDate.class), value),
                entity -> entity.get("happened"),
                "startDate"
            ),
            new FilterableInput<>(
                new TimePicker("⠀"),
                (criteria, target, value) -> criteria.greaterThanOrEqualTo(target.as(LocalTime.class), value),
                entity -> entity.get("happened"),
                "startTime"
            ),
            new FilterableInput<>(
                new RussianDatePicker("По"),
                (criteria, target, value) -> criteria.lessThanOrEqualTo(target.as(LocalDate.class), value),
                entity -> entity.get("happened"),
                "endDate"
            ),
            new FilterableInput<>(
                new TimePicker("⠀"),
                (criteria, target, value) -> criteria.lessThanOrEqualTo(target.as(LocalTime.class), value),
                entity -> entity.get("happened"),
                "endTime"
            ),
            new FilterableInput<>(
                new StandardComboBox(
                    "Заявитель",
                    new GetAllReporterNames(db).perform()
                ),
                CriteriaBuilder::equal,
                entity -> entity.get("reporter").get("name"),
                "reporterName"
            ),
            new FilterableInput<>(
                new StandardComboBox(
                    "Индекс",
                    new GetAllIndexNamesByLevel3(db).perform()
                ),
                CriteriaBuilder::equal,
                entity -> entity.get("index").get("name"),
                "indexName"
            ),
            new FilterableInput<>(
                district,
                CriteriaBuilder::equal,
                entity -> entity.get("street").get("district").get("name"),
                "districtName"
            ),
            new FilterableInput<>(
                street,
                CriteriaBuilder::equal,
                entity -> entity.get("street").get("name"),
                "streetName"
            ),
            new FilterableInput<>(
                new Checkbox("Привлечения"),
                (criteria, target, value) -> criteria.isNotEmpty(target),
                entity -> entity.<Collection<Service>>get("services"),
                "attractions"
            ),
            new FilterableInput<>(
                new Checkbox("Погибшие"),
                (criteria, target, value) -> criteria.greaterThan(target, 0),
                entity -> entity.<Integer>get("dead"),
                "dead"
            ),
            new FilterableInput<>(
                new Checkbox("Пострадавшие"),
                (criteria, target, value) -> criteria.greaterThan(target, 0),
                entity -> entity.<Integer>get("injured"),
                "injured"
            ),
            new FilterableInput<>(
                new Checkbox("Спасенные"),
                (criteria, target, value) -> criteria.greaterThan(target, 0),
                entity -> entity.<Integer>get("rescued"),
                "rescued"
            ),
            new FilterableInput<>(
                new StandardRadioButtonGroup(
                    "",
                    List.of("Завершено", "Не завершено")
                ),
                (criteria, target, value) -> criteria.equal(criteria.literal(value.equals("Завершено")), target),
                entity -> entity.get("isCompleted"),
                "completed"
            )
        );
        this.db = db;
    }

    public EntityCriteria<Emergency> criteria() {
        return new EntityCriteria<>(
            (criteria, query, entity) -> query.where(
                this.inputs.stream()
                    .map(input -> input.predicate(criteria, entity))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toArray(Predicate[]::new)
            ),
            this.db,
            Emergency.class
        );
    }

    public QueryParameters parameters() {
        return QueryParameters.simple(
            this.inputs.stream()
                .filter(FilterableInput::isNotEmpty)
                .collect(
                    Collectors.toMap(
                        FilterableInput::title,
                        input -> String.valueOf(input.value())
                    )
                )
        );
    }

    public Component[] components() {
        return this.inputs.stream()
            .map(FilterableInput::component)
            .toArray(Component[]::new);
    }
}
