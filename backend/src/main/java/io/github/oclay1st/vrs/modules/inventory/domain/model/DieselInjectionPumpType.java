package io.github.oclay1st.vrs.modules.inventory.domain.model;

public enum DieselInjectionPumpType {
    INLINE("Inline"), DISTRIBUTOR("Distributor");

    private final String text;

    private DieselInjectionPumpType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
