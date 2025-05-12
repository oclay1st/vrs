package io.github.oclay1st.vrs.modules.inventory.domain.command;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import io.github.oclay1st.vrs.modules.inventory.domain.model.BatteryType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInjectionPumpType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;

public class RegisterVehicleCommand {

    private final Long vin;

    private final String licensePlate;

    private final VehicleType type;

    private final DieselInjectionPumpType dieselInjectionPumpType;

    private final List<GasFuelType> gasFuelTypes;

    private final BatteryType batteryType;

    private final Integer batteryVoltage;

    private final Integer batteryAmperage;

    private final Boolean convertible;

    private final String convertibleLicensePlate;

    private final List<GasFuelType> convertibleGasFuelTypes;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public RegisterVehicleCommand(Long vin, String licensePlate, String type, String dieselInjectionPumpType,
            List<String> gasFuelTypes, String batteryType, Integer batteryVoltage, Integer batteryAmperage,
            Boolean convertible, String convertibleLicensePlate, List<String> convertibleGasFuelTypes) {
        this.vin = Objects.requireNonNull(vin);
        this.licensePlate = Objects.requireNonNull(licensePlate);
        this.type = VehicleType.parseValue(type, true);
        this.dieselInjectionPumpType = this.type == VehicleType.DIESEL
                ? DieselInjectionPumpType.parseValue(dieselInjectionPumpType, true)
                : null;
        this.gasFuelTypes = this.type == VehicleType.GASOLINE
                ? gasFuelTypes.stream().map(value -> GasFuelType.parseValue(value, true)).toList()
                : null;
        this.batteryType = this.type == VehicleType.ELECTRIC ? BatteryType.parseValue(batteryType, true) : null;
        this.batteryVoltage = this.type == VehicleType.ELECTRIC ? batteryVoltage : null;
        this.batteryAmperage = this.type == VehicleType.ELECTRIC ? batteryAmperage : null;
        this.convertible = this.type == VehicleType.ELECTRIC ? convertible : null;
        this.convertibleLicensePlate = this.convertible != null && this.convertible == true ? convertibleLicensePlate
                : null;
        this.convertibleGasFuelTypes = this.convertible != null && this.convertible == true
                ? convertibleGasFuelTypes.stream().map(value -> GasFuelType.parseValue(value, true)).toList()
                : null;
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

    public DieselInjectionPumpType getDieselInjectionPumpType() {
        return dieselInjectionPumpType;
    }

    public List<GasFuelType> getGasFuelTypes() {
        return gasFuelTypes;
    }

    public BatteryType getBatteryType() {
        return batteryType;
    }

    public Integer getBatteryVoltage() {
        return batteryVoltage;
    }

    public Integer getBatteryAmperage() {
        return batteryAmperage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Boolean isConvertible() {
        return convertible;
    }

    public String getConvertibleLicensePlate() {
        return convertibleLicensePlate;
    }

    public List<GasFuelType> getConvertibleGasFuelTypes() {
        return convertibleGasFuelTypes;
    }

}
