package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Service;

public class RemoveService extends Remove<Service> {
    public RemoveService(Service entity, Db db) {
        super(entity, db);
    }

    @Override
    public Void perform() {
        return this.db.execute(status -> {
            this.db.createQuery(
                "DELETE FROM Emergency AS e WHERE :service MEMBER OF e.services"
            ).setParameter("service", this.entity)
                .executeUpdate();
            super.perform();
            return null;
        });
    }
}
