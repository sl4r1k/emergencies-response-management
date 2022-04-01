package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Street;
import ru.ugochs.erm.service.Parameter;
import ru.ugochs.erm.service.SeparatedParametersWithAnd;

public class AddStreet extends AddIfNotExistsByAttributes<Street> {
    public AddStreet(Street street, Db db) {
        super(
            street,
            new SeparatedParametersWithAnd(
                new Parameter("name", street.getName()),
                new Parameter("district.name", street.getDistrict().getName())
            ),
            "Улица с таким названием и районом уже существует",
            db
        );
    }
}
