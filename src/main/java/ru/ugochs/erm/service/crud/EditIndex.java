package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;

public class EditIndex extends Edit<Index> {
    public EditIndex(Index index, Db db) {
        super(index, db);
    }

    @Override
    public Index perform() {
        return super.perform();
    }
}
