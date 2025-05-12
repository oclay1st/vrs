package io.github.oclay1st.vrs.modules.inventory.domain.model;

import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;

public enum VehicleType {
    GASOLINE("Gasoline"),
    ELECTRIC("Electric"),
    DIESEL("Diesel");

    private final String text;

    private VehicleType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    public static VehicleType parseValue(String value, boolean throwException) {
        try {
            return VehicleType.valueOf(value);
        } catch (Exception ex) {
            if (throwException)
                throw new ValidationException("Invalid vehicle type", ex);
            return null;
        }
    }

}
