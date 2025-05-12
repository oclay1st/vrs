package io.github.oclay1st.vrs.modules.inventory.domain.model;

import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;

public enum GasFuelType {
    B83("B83"), B90("B90"), B94("B94"), B100("B100");

    private final String text;

    private GasFuelType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    public static GasFuelType parseValue(String value, boolean thrownException) {
        try {
            return GasFuelType.valueOf(value);
        } catch (Exception ex) {
            if (thrownException)
                throw new ValidationException("Invalid gas fuel type", ex);
            return null;
        }
    }

}
