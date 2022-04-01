package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.District;
import java.util.List;

public class AddAllDistricts extends AddAllSkippingExisting<District> {
    public AddAllDistricts(List<District> entities, Db db) {
        super(
            entities,
            "name",
            District::getName,
            db
        );
    }
}
