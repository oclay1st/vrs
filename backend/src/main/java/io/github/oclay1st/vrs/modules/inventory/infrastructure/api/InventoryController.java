package io.github.oclay1st.vrs.modules.inventory.infrastructure.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

import io.github.oclay1st.vrs.modules.inventory.domain.model.BatteryType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.DieselInjectionPumpType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;
import io.github.oclay1st.vrs.modules.inventory.domain.service.InventoryService;
import io.github.oclay1st.vrs.modules.inventory.infrastructure.InventoryConstants;
import io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto.BatteryTypeDTO;
import io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto.DieselInjectionPumpTypeDTO;
import io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto.GasFuelTypeDTO;
import io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto.RegisterVehicleDTO;
import io.github.oclay1st.vrs.modules.inventory.infrastructure.api.dto.VehicleTypeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Inventory")
@RestController
@RequestMapping(path = InventoryConstants.BASE_PATH)
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Operation(summary = "Register a new vehicle")
    @PostMapping(path = InventoryConstants.VEHICLES_PATH)
    public void registerVehicle(@Valid @RequestBody RegisterVehicleDTO input) {
        inventoryService.registerVehicle(input.toCommand());
    }

    @Operation(summary = "Get the vehicle types")
    @GetMapping(path = InventoryConstants.VEHICLE_TYPES_PATH)
    public List<VehicleTypeDTO> listVehicleTypes() {
        return Stream.of(VehicleType.values())
                .map(VehicleTypeDTO::fromVehicleType)
                .toList();
    }

    @Operation(summary = "Get diesel pump types")
    @GetMapping(path = InventoryConstants.DIESEL_INJECTION_PUMP_TYPES_PATH)
    public List<DieselInjectionPumpTypeDTO> listDieselPumpTypes() {
        return Stream.of(DieselInjectionPumpType.values())
                .map(DieselInjectionPumpTypeDTO::fromInjectionPumpType)
                .toList();
    }

    @Operation(summary = "Get gas fuel types")
    @GetMapping(path = InventoryConstants.GAS_FUEL_TYPES_PATH)
    public List<GasFuelTypeDTO> listGasFuelTypes() {
        return Stream.of(GasFuelType.values())
                .map(GasFuelTypeDTO::fromFuelType)
                .toList();
    }

    @Operation(summary = "Get battery types")
    @GetMapping(path = InventoryConstants.BATTERY_TYPES_PATH)
    public List<BatteryTypeDTO> listBatteryTypes() {
        return Stream.of(BatteryType.values())
                .map(BatteryTypeDTO::fromBatterType)
                .toList();
    }

}
