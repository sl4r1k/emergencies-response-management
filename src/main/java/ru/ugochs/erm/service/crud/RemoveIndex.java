package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;

public class RemoveIndex extends Remove<Index> {
    public RemoveIndex(Index index, Db db) {
        super(index, db);
    }

    @Override
    public Void perform() {
        return super.perform();
    }
}
