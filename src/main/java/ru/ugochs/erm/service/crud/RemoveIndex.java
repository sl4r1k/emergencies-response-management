package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;

public class RemoveIndex extends Remove<Index> {
    public RemoveIndex(Index entity, Db db) {
        super(entity, db);
    }

    @Override
    public Void perform() {
        return this.db.execute(status -> {
            this.db.createQuery(
                "DELETE FROM Emergency AS e WHERE e.index = :index"
            ).setParameter("index", this.entity)
                .executeUpdate();
            super.perform();
            return null;
        });
    }
}
