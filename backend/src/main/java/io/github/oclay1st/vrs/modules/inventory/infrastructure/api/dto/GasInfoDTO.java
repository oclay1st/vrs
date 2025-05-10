package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import java.util.List;

import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasInfo;

public record GasInfoDTO(List<String> fuelTypes) {

    public GasInfo toInfo() {
        List<GasFuelType> gasFuelTypes = fuelTypes.stream()
                .map(GasFuelType::valueOf)
                .toList();
        return new GasInfo(gasFuelTypes);
    }

}
