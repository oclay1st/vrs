package io.github.oclay1st.vrs.modules.inventory.domain.criteria;

import java.util.List;

import io.github.oclay1st.vrs.modules.inventory.domain.model.BatteryType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInjectionPumpType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;

public record VehicleCriteria(Long vin, String licensePlate, VehicleType type,
        DieselInjectionPumpType dieselInjectionPumpType,
        List<GasFuelType> gasFuelTypes, BatteryType batteryType, Integer batteryVoltage, Integer batteryAmperage,
        Long userId) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long vin;

        private String licensePlate;

        private VehicleType type;

        private DieselInjectionPumpType dieselInjectionPumpType;

        private List<GasFuelType> gasFuelTypes;

        private BatteryType batteryType;

        private Integer batteryVoltage;

        private Integer batteryAmperage;

        private Long userId;

        public Builder vin(Long vin) {
            this.vin = vin;
            return this;
        }

        public Builder licensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder type(VehicleType type) {
            this.type = type;
            return this;
        }

        public Builder dieselInjectionPumpType(DieselInjectionPumpType dieselInjectionPumpType) {
            this.dieselInjectionPumpType = dieselInjectionPumpType;
            return this;
        }

        public Builder gasFuelTypes(List<GasFuelType> gasFuelTypes) {
            this.gasFuelTypes = gasFuelTypes;
            return this;
        }

        public Builder batteryType(BatteryType batteryType) {
            this.batteryType = batteryType;
            return this;
        }

        public Builder batteryVoltage(Integer batteryVoltage) {
            this.batteryVoltage = batteryVoltage;
            return this;
        }

        public Builder batteryAmperage(Integer batteryAmperage) {
            this.batteryAmperage = batteryAmperage;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public VehicleCriteria build() {
            return new VehicleCriteria(vin, licensePlate, type, dieselInjectionPumpType, gasFuelTypes, batteryType,
                    batteryVoltage, batteryAmperage, userId);
        }

    }

}
