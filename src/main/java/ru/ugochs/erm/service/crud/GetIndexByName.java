package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Index;

public class GetIndexByName extends GetSingle<Index> {
    public GetIndexByName(String name, Db db) {
        super("name", name, db, Index.class);
    }
}
