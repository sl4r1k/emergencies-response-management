package ru.ugochs.erm.service;

import ru.ugochs.erm.service.crud.Db;

public abstract class XlsImport {
    protected final Db db;

    public XlsImport(Db db) {
        this.db = db;
    }

    public abstract void act();
}
