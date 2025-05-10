package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;

public record VehicleTypeDTO(String name, String value) {

    public static VehicleTypeDTO fromVehicleType(VehicleType type) {
        return new VehicleTypeDTO(type.text(), type.name());
    }

}
