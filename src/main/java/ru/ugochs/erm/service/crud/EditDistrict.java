package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.District;

public class EditDistrict extends EditIfNotExists<District> {
    public EditDistrict(District district, Db db) {
        super(
            district,
            "name",
            district.getName(),
            district.getId(),
            "Заявитель с таким названием уже существует",
            db
        );
    }
}
