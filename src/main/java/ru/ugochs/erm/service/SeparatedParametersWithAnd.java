package ru.ugochs.erm.service;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SeparatedParametersWithAnd implements Parameters {
    private final List<Parameter> list;

    public SeparatedParametersWithAnd(Parameter... array) {
        this.list = List.of(array);
    }

    @Override
    public String asString() {
        return String.join(
            " AND ", this.list.stream()
                .map(Parameter::asString)
                .collect(Collectors.toList())
        );
    }

    @Override
    public Iterator<Parameter> iterator() {
        return this.list.iterator();
    }

    @Override
    public void forEach(Consumer<? super Parameter> action) {
        this.list.forEach(action);
    }
}
