package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.inventory.domain.model.ElectricInfo;
import io.github.oclay1st.vrs.modules.inventory.domain.model.BatteryType;

public record ElectricInfoDTO(String batteryType, Float voltage, Float amperage) {

    public ElectricInfo toInfo() {
        return new ElectricInfo(BatteryType.valueOf(batteryType), voltage, amperage);
    }

}
