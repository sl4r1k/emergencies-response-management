package ru.ugochs.erm.view.util;

public enum ReportDataIdentifier {
    EMERGENCIES("ПР"),
    SERVICES("СЭР");

    private final String title;

    ReportDataIdentifier(String title) {
        this.title = title;
    }

    public String title() {
        return this.title;
    }
}
