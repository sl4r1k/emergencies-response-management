package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;
import javax.persistence.EntityExistsException;

public class EditIndex extends Edit<Index> {
    public EditIndex(Index index, Db db) {
        super(index, db);
    }

    @Override
    public Index perform() {
        if (new ExistsByAttributeIgnoringItself<>(
            "name", this.entity.getName(), this.entity.getId(),
            this.db, Index.class
        ).perform()) {
            throw new EntityExistsException(
                "Индекс с таким названием уже существует"
            );
        }
        return super.perform();
    }
}
