package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Street;
import java.util.List;

public class GetAllStreetNamesByDistrictName extends CrudOperation<Street, List<String>> {
    private final String districtName;

    public GetAllStreetNamesByDistrictName(String districtName, Db db) {
        super(db);
        this.districtName = districtName;
    }

    @Override
    public List<String> perform() {
        return this.db.createQuery(
            "SELECT s.name FROM Street AS s WHERE s.district.name = :districtName",
            String.class
        )
            .setParameter("districtName", this.districtName)
            .getResultList();
    }
}
