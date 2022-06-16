package ru.ugochs.erm.view.util;

import lombok.Getter;
import ru.ugochs.erm.entity.*;

@Getter
public class ReportData {
    private final String index;
    private final String district;
    private final String identifier;
    private final long quantity;

    public ReportData(Emergency emergency, ReportDataIdentifier identifier) {
        this.index = emergency.getIndex().getName();
        this.district = emergency.getStreet().getDistrict().getName();
        this.identifier = identifier.title();
        this.quantity = ReportDataIdentifier.EMERGENCIES.equals(identifier) ? 1 : emergency.getServices().size();
    }

    public String getIndex() {
        return this.index;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public long getQuantity() {
        return this.quantity;
    }
}
