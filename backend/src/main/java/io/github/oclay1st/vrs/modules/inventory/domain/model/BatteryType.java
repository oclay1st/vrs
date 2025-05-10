package io.github.oclay1st.vrs.modules.inventory.domain.model;

public enum BatteryType {
    LITHIUM("Lithium"), GEL("Gel");

    private final String text;

    private BatteryType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
