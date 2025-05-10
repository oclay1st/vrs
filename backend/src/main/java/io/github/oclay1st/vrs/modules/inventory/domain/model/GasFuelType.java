package io.github.oclay1st.vrs.modules.inventory.domain.model;

public enum GasFuelType {
    B83("B83"), B90("B90"), B94("B94"), B100("B100");

    private final String text;

    private GasFuelType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
