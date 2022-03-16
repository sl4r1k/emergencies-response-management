package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;
import javax.persistence.NoResultException;
import java.util.Optional;

public class GetIndexByName extends GetSingle<Index> {
    private final String name;

    public GetIndexByName(String name, Db db) {
        super(db);
        this.name = name;
    }

    @Override
    public Optional<Index> perform() {
        try {
            return Optional.of(
                new EntityCriteria<>((criteria, query, index) -> query.select(index).where(
                    criteria.equal(
                        index.get("name"), this.name
                    )
                ),
                    this.db, Index.class
                ).query().getSingleResult()
            );
        } catch (NoResultException exception) {
            return Optional.empty();
        }
    }
}
