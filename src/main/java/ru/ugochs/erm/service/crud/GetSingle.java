package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import java.util.Optional;

public abstract class GetSingle<T extends AbstractEntity> extends CrudOperation<T, Optional<T>> {
    protected GetSingle(Db db) {
        super(db);
    }
}
