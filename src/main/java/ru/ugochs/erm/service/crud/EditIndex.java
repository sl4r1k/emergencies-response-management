package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;

public class EditIndex extends EditIfNotExists<Index> {
    public EditIndex(Index index, Db db) {
        super(
            index,
            "name",
            index.getName(),
            index.getId(),
            "Индекс с таким названием уже существует",
            db
        );
    }
}
