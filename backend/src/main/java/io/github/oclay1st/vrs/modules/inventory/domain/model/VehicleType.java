package io.github.oclay1st.vrs.modules.inventory.domain.model;

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
}
