package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.AbstractEntity;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class AddAll<T extends AbstractEntity> extends CrudOperation<T, List<T>> {
    protected final List<T> entities;
    private final BiFunction<? super T, ? super Db, ? extends Add<T>> operation;

    protected AddAll(
        List<T> entities, BiFunction<? super T, ? super Db, ? extends Add<T>> operation,
        Db db
    ) {
        super(db);
        this.entities = entities;
        this.operation = operation;
    }

    @Override
    public List<T> perform() {
        return this.db.execute(action -> {
            this.entities.forEach(
                entity -> this.operation.apply(
                    entity,
                    this.db
                ).perform()
            );
            return Collections.unmodifiableList(this.entities);
        });
    }
}
