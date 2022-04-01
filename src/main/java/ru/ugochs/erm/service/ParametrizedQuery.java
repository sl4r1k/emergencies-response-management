package ru.ugochs.erm.service;

import javax.persistence.TypedQuery;

public class ParametrizedQuery<T> {
    private final TypedQuery<T> query;
    private final Parameters parameters;

    public ParametrizedQuery(TypedQuery<T> query, Parameters parameters) {
        this.query = query;
        this.parameters = parameters;
    }

    public TypedQuery<T> query() {
        this.parameters.forEach(parameter -> this.query.setParameter(
            parameter.attribute(),
            parameter.value()
        ));
        return this.query;
    }
}
