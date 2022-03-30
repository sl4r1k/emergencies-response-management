package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.District;

public class AddDistrict extends AddIfNotExists<District> {
    public AddDistrict(District district, Db db) {
        super(
            district,
            "name",
            district.getName(),
            "Район с таким названием уже существует",
            db
        );
    }
}
