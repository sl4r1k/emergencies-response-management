package ru.ugochs.erm.entity;

public enum Role {
    ADMIN("ROLE_ADMIN", "Администратор"),
    USER("ROLE_USER", "Пользователь");

    private final String systemName;
    private final String russianName;

    Role(String systemName, String russianName) {
        this.systemName = systemName;
        this.russianName = russianName;
    }

    public String systemName() {
        return this.systemName;
    }

    public String russianName() {
        return this.russianName;
    }
}
