package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;

public class AddIndex extends Add<Index> {
    public AddIndex(Index index, Db db) {
        super(index, db);
    }

    @Override
    public Index perform() {
        return super.perform();
    }
}
