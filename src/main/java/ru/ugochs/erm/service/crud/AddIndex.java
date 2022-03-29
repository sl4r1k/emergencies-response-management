package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;
import javax.persistence.EntityExistsException;

public class AddIndex extends Add<Index> {
    public AddIndex(Index index, Db db) {
        super(index, db);
    }

    @Override
    public Index perform() {
        if (new ExistsByAttribute<>(
            "name", this.entity.getName(),
            this.db, Index.class
        ).perform()) {
            throw new EntityExistsException(
                "Индекс с таким названием уже существует"
            );
        }
        return super.perform();
    }
}
