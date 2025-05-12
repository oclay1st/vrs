package io.github.oclay1st.vrs.modules.inventory.domain.model;

import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;

public enum DieselInjectionPumpType {
    INLINE("Inline"), DISTRIBUTOR("Distributor");

    private final String text;

    private DieselInjectionPumpType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    public static DieselInjectionPumpType parseValue(String value, boolean throwException) {
        try {
            return DieselInjectionPumpType.valueOf(value);
        } catch (Exception ex) {
            if (throwException)
                throw new ValidationException("Invalid injection pump type", ex);
            return null;
        }
    }

}
