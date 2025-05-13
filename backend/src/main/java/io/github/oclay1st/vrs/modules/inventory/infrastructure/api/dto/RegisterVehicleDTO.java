package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import java.util.List;

import io.github.oclay1st.vrs.modules.inventory.domain.command.RegisterVehicleCommand;

public record RegisterVehicleDTO(Long vin, String licensePlate, String type, String dieselInjectionPumpType,
        List<String> gasFuelTypes, String batteryType, Integer batteryVoltage, Integer batteryAmperage,
        Boolean convertible, String convertibleLicensePlate, List<String> convertibleGasFuelTypes) {

    public RegisterVehicleCommand toCommand(Long userId) {
        return new RegisterVehicleCommand(vin, licensePlate, type, dieselInjectionPumpType, gasFuelTypes,
                batteryType, batteryVoltage, batteryAmperage, convertible, convertibleLicensePlate,
                convertibleGasFuelTypes, userId);
    }

}
