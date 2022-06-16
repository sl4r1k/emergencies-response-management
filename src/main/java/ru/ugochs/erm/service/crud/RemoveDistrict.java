package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.District;

public class RemoveDistrict extends Remove<District> {
    public RemoveDistrict(District entity, Db db) {
        super(entity, db);
    }

    @Override
    public Void perform() {
        return this.db.execute(status -> {
            this.db.createQuery(
                "DELETE FROM Emergency AS e WHERE e.street.district = :district"
            ).setParameter("district", this.entity)
                .executeUpdate();
            this.db.createQuery(
                "DELETE FROM Street AS s WHERE s.district = :district"
            ).setParameter("district", this.entity)
                .executeUpdate();
            super.perform();
            return null;
        });
    }
}
