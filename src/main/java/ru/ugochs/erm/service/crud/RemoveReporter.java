package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Reporter;

public class RemoveReporter extends Remove<Reporter> {
    public RemoveReporter(Reporter entity, Db db) {
        super(entity, db);
    }

    @Override
    public Void perform() {
        return this.db.execute(status -> {
            this.db.createQuery(
                    "DELETE FROM Emergency AS e WHERE e.reporter = :reporter"
                ).setParameter("reporter", this.entity)
                .executeUpdate();
            super.perform();
            return null;
        });
    }
}
