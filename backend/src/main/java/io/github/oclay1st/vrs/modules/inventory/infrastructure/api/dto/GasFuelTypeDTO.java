package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;

public record GasFuelTypeDTO(String name, String value) {

    public static GasFuelTypeDTO fromFuelType(GasFuelType fuelType) {
        return new GasFuelTypeDTO(fuelType.text(), fuelType.name());
    }

}
