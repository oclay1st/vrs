package io.github.oclay1st.vrs.modules.inventory.domain.command;

import java.time.LocalDateTime;
import java.util.Objects;

import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInfo;
import io.github.oclay1st.vrs.modules.inventory.domain.model.ElectricInfo;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasInfo;
import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;

public class RegisterVehicleCommand {

    private final Long vin;

    private final String licensePlate;

    private final VehicleType type;

    private final DieselInfo dieselInfo;

    private final GasInfo gasInfo;

    private final ElectricInfo batteryInfo;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public RegisterVehicleCommand(Long vin, String licensePlate, VehicleType type, DieselInfo dieselInfo,
            GasInfo gasInfo, ElectricInfo batteryInfo) {
        this.vin = Objects.requireNonNull(vin);
        this.licensePlate = Objects.requireNonNull(licensePlate);
        this.type = Objects.requireNonNull(type);
        this.dieselInfo = type == VehicleType.DIESEL ? Objects.requireNonNull(dieselInfo) : null;
        this.gasInfo = type == VehicleType.GASOLINE ? Objects.requireNonNull(gasInfo) : null;
        this.batteryInfo = type == VehicleType.ELECTRIC ? Objects.requireNonNull(batteryInfo) : null;
        LocalDateTime currentTime = LocalDateTime.now();
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    public Long getVin() {
        return vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public DieselInfo getDieselInfo() {
        return dieselInfo;
    }

    public GasInfo getGasInfo() {
        return gasInfo;
    }

    public ElectricInfo getElectricInfo() {
        return batteryInfo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
