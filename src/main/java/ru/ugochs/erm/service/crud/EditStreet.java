package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Street;
import ru.ugochs.erm.service.*;

public class EditStreet extends EditIfNotExistsByAttributes<Street> {
    public EditStreet(Street street, Db db) {
        super(
            street,
            new SeparatedParametersWithAnd(
                new Parameter("name", street.getName()),
                new Parameter("district.name", street.getDistrict().getName())
            ),
            street.getId(),
            "Улица с таким названием и районом уже существует",
            db
        );
    }
}
