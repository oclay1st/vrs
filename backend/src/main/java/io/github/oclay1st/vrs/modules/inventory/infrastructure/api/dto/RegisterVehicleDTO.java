package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import io.github.oclay1st.vrs.modules.inventory.domain.command.RegisterVehicleCommand;
import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInfo;
import io.github.oclay1st.vrs.modules.inventory.domain.model.ElectricInfo;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasInfo;
import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;

public record RegisterVehicleDTO(Long vin, String licensePlate, String type, DieselInfoDTO dieselInfo,
        GasInfoDTO gasInfo, ElectricInfoDTO electricInfo) {

    public RegisterVehicleCommand toCommand() {
        VehicleType vehicleType = VehicleType.valueOf(type);
        DieselInfo diesel = vehicleType == VehicleType.DIESEL ? dieselInfo.toInfo() : null;
        GasInfo gas = vehicleType == VehicleType.GASOLINE ? gasInfo.toInfo() : null;
        ElectricInfo electric = vehicleType == VehicleType.ELECTRIC ? electricInfo.toInfo() : null;
        return new RegisterVehicleCommand(vin, licensePlate, vehicleType, diesel, gas, electric);
    }

}
