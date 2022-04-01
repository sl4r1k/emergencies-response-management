package ru.ugochs.erm.service;

public class Parameter {
    private final String attribute;
    private final Object value;

    public Parameter(String attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String asString() {
        return String.format(
            "x.%s = :%s",
            this.attribute,
            this.attribute()
        );
    }

    public String attribute() {
        return this.attribute.replaceAll("\\.", "");
    }

    public Object value() {
        return this.value;
    }
}
