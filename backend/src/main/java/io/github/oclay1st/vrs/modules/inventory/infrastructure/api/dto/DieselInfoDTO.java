package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInfo;
import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInjectionPumpType;

public record DieselInfoDTO(String injectionPumpType) {

    public DieselInfo toInfo() {
        return new DieselInfo(DieselInjectionPumpType.valueOf(injectionPumpType));
    }

}
