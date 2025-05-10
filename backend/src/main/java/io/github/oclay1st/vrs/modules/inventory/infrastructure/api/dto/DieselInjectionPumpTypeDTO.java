package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInjectionPumpType;

public record DieselInjectionPumpTypeDTO(String name, String value) {

    public static DieselInjectionPumpTypeDTO fromInjectionPumpType(DieselInjectionPumpType injectionPumpType) {
        return new DieselInjectionPumpTypeDTO(injectionPumpType.text(), injectionPumpType.name());
    }

}
