package ru.ugochs.erm.service.crud;

import org.hibernate.Session;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.service.crud.*;
import java.time.LocalDate;
import java.util.List;

public class GetAllEmergenciesByPeriod extends GetAll<Emergency> {
    private final LocalDate from;
    private final LocalDate to;

    public GetAllEmergenciesByPeriod(LocalDate from, LocalDate to, Db db) {
        super(db, Emergency.class);
        this.from = from;
        this.to = to;
    }

    @Override
    public List<Emergency> perform() {
        return this.db.unwrap(Session.class)
            .createQuery("SELECT e FROM Emergency AS e WHERE CAST(e.happened AS LocalDate) BETWEEN :from AND :to")
            .setParameter("from", this.from)
            .setParameter("to", this.to)
            .getResultList();
    }
}
