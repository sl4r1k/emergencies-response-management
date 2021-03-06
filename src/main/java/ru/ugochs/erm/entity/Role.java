package ru.ugochs.erm.entity;

import java.util.List;

public enum Role {
    ADMIN("ROLE_ADMIN", "Администратор"),
    USER("ROLE_USER", "Пользователь");

    private final String systemName;
    private final String russianName;

    Role(String systemName, String russianName) {
        this.systemName = systemName;
        this.russianName = russianName;
    }

    public static Role valueOfRussianName(String russianName) {
        return Role.ADMIN.russianName.equals(russianName)
            ? Role.ADMIN
            : Role.USER;
    }

    public static List<String> russianNames() {
        return List.of(
            Role.ADMIN.russianName,
            Role.USER.russianName
        );
    }

    public String systemName() {
        return this.systemName;
    }

    public String russianName() {
        return this.russianName;
    }
}
