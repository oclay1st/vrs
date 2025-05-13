package io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.Objects;

import io.github.oclay1st.vrs.modules.common.PageCriteria;
import io.github.oclay1st.vrs.modules.common.Utils;
import io.github.oclay1st.vrs.modules.inventory.domain.criteria.VehicleCriteria;
import io.github.oclay1st.vrs.modules.inventory.domain.model.BatteryType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInjectionPumpType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public class VehicleQueryParams {

    private String vin;

    private String licensePlate;

    private String type;

    private String dieselInjectionPumpType;

    private List<String> gasFuelTypes;

    private String batteryType;

    private Integer batteryVoltage;

    private Integer batteryAmperage;

    @Parameter(name = "page", schema = @Schema(defaultValue = "1"))
    private Integer pageNo = 1;

    @Parameter(name = "size", schema = @Schema(defaultValue = "20"))
    private Integer pageSize = 20;

    public VehicleCriteria toVehicleCriteria(Long userId) {
        Long vinValue = Utils.parseLong(vin);
        VehicleType typeValue = VehicleType.parseValue(type, false);
        DieselInjectionPumpType pumpTypeValue = DieselInjectionPumpType.parseValue(dieselInjectionPumpType, false);
        List<GasFuelType> gasFuelTypesValue = null;
        if (gasFuelTypes != null) {
            gasFuelTypesValue = gasFuelTypes.stream().map(v -> GasFuelType.parseValue(v, false)).toList();
        }
        BatteryType batteryTypeValue = BatteryType.parseValue(batteryType, false);
        return new VehicleCriteria(vinValue, licensePlate, typeValue, pumpTypeValue, gasFuelTypesValue,
                batteryTypeValue, batteryVoltage, batteryAmperage, userId);
    }

    public PageCriteria toPageCriteria() {
        return new PageCriteria(pageNo, pageSize);
    }

    @JsonCreator
    public VehicleQueryParams(String vin, String licensePlate, String type, String dieselInjectionPumpType,
            List<String> gasFuelTypes, String batteryType, Integer batteryVoltage, Integer batteryAmperage,
            Integer pageNo, Integer pageSize) {
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.type = type;
        this.dieselInjectionPumpType = dieselInjectionPumpType;
        this.gasFuelTypes = gasFuelTypes;
        this.batteryType = batteryType;
        this.batteryVoltage = batteryVoltage;
        this.batteryAmperage = batteryAmperage;
        this.pageNo = Objects.requireNonNullElse(pageNo, this.pageNo);
        this.pageSize = Objects.requireNonNullElse(pageSize, this.pageSize);
    }

}
