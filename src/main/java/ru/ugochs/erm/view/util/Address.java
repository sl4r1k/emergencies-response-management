package ru.ugochs.erm.view.util;

import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.entity.Street;
import java.util.Optional;

public class Address {
    private final Street street;
    private final Optional<String> house;
    private final Optional<String> houseFraction;
    private final Optional<String> floor;

    public Address(Emergency emergency) {
        this.street = emergency.getStreet();
        this.house = Optional.ofNullable(emergency.getHouse());
        this.houseFraction = Optional.ofNullable(emergency.getHouseFraction());
        this.floor = Optional.ofNullable(emergency.getFloor());
    }

    @Override
    public String toString() {
        return String.format("%s %s%s %s",
            this.street.getName(),
            this.house.map(s -> "д. " + s).orElse(""),
            this.houseFraction.map(s -> "/" + s).orElse(""),
            this.floor.map(s -> "кв. " + s).orElse("")
        );
    }
}
