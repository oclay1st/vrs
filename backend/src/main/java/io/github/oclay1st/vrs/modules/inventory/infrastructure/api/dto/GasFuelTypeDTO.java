package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;

public record GasFuelTypeDTO(String name, String value) {

    public static GasFuelTypeDTO fromFuelType(GasFuelType type) {
        return new GasFuelTypeDTO(type.text(), type.name());
    }

}
