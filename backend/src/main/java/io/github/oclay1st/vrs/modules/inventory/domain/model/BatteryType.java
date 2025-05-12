package io.github.oclay1st.vrs.modules.inventory.domain.model;

import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;

public enum BatteryType {
    LITHIUM("Lithium"), GEL("Gel");

    private final String text;

    private BatteryType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    public static BatteryType parseValue(String value, boolean thrownException) {
        try {
            return BatteryType.valueOf(value);
        } catch (Exception ex) {
            if (thrownException)
                throw new ValidationException("Invalid battery type", ex);
            return null;
        }
    }

}
