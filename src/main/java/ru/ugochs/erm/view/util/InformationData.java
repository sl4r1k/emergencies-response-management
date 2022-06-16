package ru.ugochs.erm.view.util;

import ru.ugochs.erm.entity.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class InformationData {
    private final District district;
    private final Integer number;
    private final Integer numberByDistrict;
    private final LocalDateTime happened;
    private final Index index;
    private final Address address;
    private final String description;
    private final List<Service> services;

    public InformationData(Emergency emergency, Integer number, Integer numberByDistrict) {
        this.district = emergency.getStreet().getDistrict();
        this.number = number;
        this.numberByDistrict = numberByDistrict;
        this.happened = emergency.getHappened();
        this.index = emergency.getIndex();
        this.address = new Address(emergency);
        this.description = emergency.getDescription();
        this.services = emergency.getServices();
    }

    public String getNumber() {
        return String.valueOf(this.number);
    }

    public String getNumberByDistrict() {
        return String.valueOf(this.numberByDistrict);
    }

    public String getHappened() {
        return new LocalDateTimeAsRussianString(
            this.happened
        ).toString();
    }

    public String getIndex() {
        return this.index.getName();
    }

    public String getAddress() {
        return this.address.toString();
    }

    public String getDescription() {
        return this.description;
    }

    public String getServices() {
        return this.services.stream()
            .map(Service::getName)
            .collect(Collectors.joining(", "));
    }

    public String getDistrict() {
        return this.district.getName();
    }
}
