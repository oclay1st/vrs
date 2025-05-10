package io.github.oclay1st.vrs.modules.inventory.domain.repository;

import io.github.oclay1st.vrs.modules.inventory.domain.command.RegisterVehicleCommand;

public interface InventoryRepository {

    void registerVehicle(RegisterVehicleCommand command);

    boolean existsByVIN(Long vin);

    boolean existsByLicensePlate(String licensePlate);
}
