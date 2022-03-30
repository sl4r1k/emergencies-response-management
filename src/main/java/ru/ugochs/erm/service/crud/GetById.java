package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import java.util.Optional;

public class GetById<T extends AbstractEntity> extends GetSingle<T> {
    protected final Long id;
    protected final Class<T> type;

    public GetById(Long id, Db db, Class<T> type) {
        super(null, null, db, null);
        this.id = id;
        this.type = type;
    }

    @Override
    public Optional<T> perform() {
        return Optional.ofNullable(this.db.find(this.type, this.id));
    }
}
