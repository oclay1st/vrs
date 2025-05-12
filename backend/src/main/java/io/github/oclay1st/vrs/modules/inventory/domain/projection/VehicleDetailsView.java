package io.github.oclay1st.vrs.modules.inventory.domain.projection;

import java.time.LocalDateTime;
import java.util.List;

import io.github.oclay1st.vrs.modules.inventory.domain.model.BatteryType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInjectionPumpType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;

public record VehicleDetailsView(Long vin, String licensePlate, VehicleType type,
        DieselInjectionPumpType dieselInjectionPumpType, List<GasFuelType> gasFuelTypes, BatteryType batteryType,
        Integer batteryVoltage, Integer batteryAmperage, Boolean convertible, String convertibleLicensePlate,
        List<GasFuelType> convertibleGasFuelTypes, LocalDateTime createdAt, LocalDateTime updatedAt) {

}
