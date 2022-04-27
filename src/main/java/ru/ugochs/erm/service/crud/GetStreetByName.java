package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Street;

public class GetStreetByName extends GetSingle<Street> {
    public GetStreetByName(String name, Db db) {
        super("name", name, db, Street.class);
    }
}
