package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Service;

public class EditService extends EditIfNotExists<Service> {
    public EditService(Service service, Db db) {
        super(
            service,
            "name",
            service.getName(),
            service.getId(),
            "Служба с таким названием уже существует",
            db
        );
    }

    @Override
    public Service perform() {
        new GetReporterByName(
            new GetById<>(
                this.entity.getId(),
                this.db,
                Service.class
            ).perform().get().getName(),
            this.db
        ).perform().ifPresent(reporter -> {
            reporter.setName(this.entity.getName());
            new EditReporter(
                reporter,
                this.db
            ).perform();
        });
        return super.perform();
    }
}
