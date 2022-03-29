package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;

public class AddIndex extends AddIfNotExists<Index> {
    public AddIndex(Index index, Db db) {
        super(
            index,
            "name",
            index.getName(),
            "Индекс с таким названием уже существует",
            db
        );
    }
}
