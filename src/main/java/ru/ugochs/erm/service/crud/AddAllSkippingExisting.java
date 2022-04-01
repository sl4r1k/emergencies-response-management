package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import java.util.List;
import java.util.function.Function;

public class AddAllSkippingExisting<T extends AbstractEntity> extends AddAll<T> {
    protected AddAllSkippingExisting(
        List<T> entities,
        String attribute,
        Function<? super T, Object> value,
        Db db
    ) {
        super(
            entities,
            (entity, dbb) -> new AddOrIgnoreIfNotExists<>(
                entity,
                attribute,
                value.apply(entity),
                dbb
            ),
            db
        );
    }
}
