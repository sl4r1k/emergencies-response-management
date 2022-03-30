package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Reporter;
import ru.ugochs.erm.entity.Service;
import javax.persistence.EntityExistsException;

public class AddService extends AddIfNotExists<Service> {
    public AddService(Service service, Db db) {
        super(
            service,
            "name",
            service.getName(),
            "Служба с таким названием уже существует",
            db
        );
    }

    @Override
    public Service perform() {
        try {
            new AddReporter(
                new Reporter(this.entity.getName()),
                this.db
            ).perform();
        } catch (EntityExistsException ignored) {}
        return super.perform();
    }
}
