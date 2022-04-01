package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.District;

public class GetDistrictByName extends GetSingle<District> {
    public GetDistrictByName(String name, Db db) {
        super("name", name, db, District.class);
    }
}
