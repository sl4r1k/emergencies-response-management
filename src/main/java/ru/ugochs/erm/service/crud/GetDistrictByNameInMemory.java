package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.District;
import java.util.List;
import java.util.Optional;

public class GetDistrictByNameInMemory extends GetSingle<District> {
    private final List<District> districts;
    private final String name;

    public GetDistrictByNameInMemory(List<District> districts, String name) {
        super(null, null, null, District.class);
        this.districts = districts;
        this.name = name;
    }

    @Override
    public Optional<District> perform() {
        return this.districts.stream()
            .filter(district -> district.getName().equals(this.name))
            .findFirst();
    }
}
