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
}
