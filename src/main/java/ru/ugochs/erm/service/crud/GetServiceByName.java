package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Service;

public class GetServiceByName extends GetSingle<Service> {
    public GetServiceByName(String name, Db db) {
        super("name", name, db, Service.class);
    }
}
