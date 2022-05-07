package ru.ugochs.erm.view.form;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import org.vaadin.gatanaso.MultiselectComboBox;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAllServiceNames;
import ru.ugochs.erm.view.component.*;
import ru.ugochs.erm.view.convert.*;
import ru.ugochs.erm.view.util.BindedInputWithConverter;
import ru.ugochs.erm.view.util.StandardBindedInput;
import java.util.HashSet;

public abstract class EmergencyForm extends Form<Emergency> {
    protected final ComboBox<String> index;
    protected final ComboBox<String> reporter;
    protected final ComboBox<String> street;
    protected final MultiselectComboBox<String> services;

    protected EmergencyForm(Db db) {
        super(db, "Происшествие", Emergency.class);
        this.index = new BindedInputWithConverter<>(
            new StandardComboBox("Индекс"),
            this.binder,
            new StringAsIndex(this.db),
            Emergency::getIndex,
            Emergency::setIndex,
            true
        ).bind();
        this.reporter = new BindedInputWithConverter<>(
            new StandardComboBox("Заявитель"),
            this.binder,
            new StringAsReporter(this.db),
            Emergency::getReporter,
            Emergency::setReporter,
            true
        ).bind();
        this.street = new BindedInputWithConverter<>(
            new StandardComboBox("Улица"),
            this.binder,
            new StringAsStreet(this.db),
            Emergency::getStreet,
            Emergency::setStreet,
            true
        ).bind();
        this.services = new BindedInputWithConverter<>(
            new MultiselectComboBox<>("Привлеченные службы"),
            this.binder,
            new StringsAsServices(this.db),
            emergency -> new HashSet<>(emergency.getServices()),
            (emergency, sers) -> {
                emergency.getServices().clear();
                emergency.getServices().addAll(sers);
            },
            false
        ).bind();
        this.services.setItems(new GetAllServiceNames(this.db).perform());
        this.add(
            new StandardFormLayout(
                new StandardBindedInput<>(
                    new RussianDateTimePicker("Случилось"),
                    this.binder,
                    Emergency::getHappened,
                    Emergency::setHappened,
                    true
                ).bind(),
                this.index,
                new StandardBindedInput<>(
                    new TextArea("Описание"),
                    this.binder,
                    Emergency::getDescription,
                    Emergency::setDescription,
                    false
                ).bind(),
                this.reporter,
                this.street,
                new HorizontalLayout(
                    new StandardBindedInput<>(
                        new WidthedTextField("Дом", 20),
                        this.binder,
                        Emergency::getHouse,
                        Emergency::setHouse,
                        false
                    ).bind(),
                    new StandardBindedInput<>(
                        new WidthedTextField("/", 20),
                        this.binder,
                        Emergency::getHouseFraction,
                        Emergency::setHouseFraction,
                        false
                    ).bind(),
                    new StandardBindedInput<>(
                        new WidthedTextField("Квартира", 20),
                        this.binder,
                        Emergency::getFloor,
                        Emergency::setFloor,
                        false
                    ).bind()
                ),
                this.services,
                new HorizontalLayout(
                    new StandardBindedInput<>(
                        new WidthedIntegerField("Погибло", 25),
                        this.binder,
                        Emergency::getDead,
                        Emergency::setDead,
                        true
                    ).bind(),
                    new StandardBindedInput<>(
                        new WidthedIntegerField("Пострадало", 25),
                        this.binder,
                        Emergency::getInjured,
                        Emergency::setInjured,
                        true
                    ).bind(),
                    new StandardBindedInput<>(
                        new WidthedIntegerField("Спасено", 25),
                        this.binder,
                        Emergency::getRescued,
                        Emergency::setRescued,
                        true
                    ).bind()
                ),
                new StandardBindedInput<>(
                    new Checkbox("Завершено"),
                    this.binder,
                    Emergency::getIsCompleted,
                    Emergency::setIsCompleted,
                    false
                ).bind()
            )
        );
    }
}
