package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Service;

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
}
