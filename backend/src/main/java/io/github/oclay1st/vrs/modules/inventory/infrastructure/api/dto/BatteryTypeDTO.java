package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.inventory.domain.model.BatteryType;

public record BatteryTypeDTO(String name, String value) {

    public static BatteryTypeDTO fromBatterType(BatteryType type) {
        return new BatteryTypeDTO(type.text(), type.name());
    }

}
