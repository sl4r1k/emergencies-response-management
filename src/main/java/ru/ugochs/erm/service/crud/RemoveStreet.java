package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Street;

public class RemoveStreet extends Remove<Street> {
    public RemoveStreet(Street entity, Db db) {
        super(entity, db);
    }

    @Override
    public Void perform() {
        return this.db.execute(status -> {
            this.db.createQuery(
                    "DELETE FROM Emergency AS e WHERE e.street = :street"
                ).setParameter("street", this.entity)
                .executeUpdate();
            super.perform();
            return null;
        });
    }
}
