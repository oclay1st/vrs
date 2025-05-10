package io.github.oclay1st.vrs.modules.inventory.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;
import io.github.oclay1st.vrs.modules.inventory.domain.command.RegisterVehicleCommand;
import io.github.oclay1st.vrs.modules.inventory.domain.repository.InventoryRepository;

@Service
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Register a a new vehicle as part of the inventory
     *
     * @param command a {@link RegisterVehicleCommand}
     */
    @Transactional
    public void registerVehicle(RegisterVehicleCommand command) {
        if (inventoryRepository.existsByVIN(command.getVin())) {
            throw new ValidationException("Vehicle already exists with the given VIN: " + command.getVin());
        }
        if (inventoryRepository.existsByLicensePlate(command.getLicensePlate())) {
            throw new ValidationException(
                    "Vehicle already exists with the given license plate: " + command.getLicensePlate());
        }
        inventoryRepository.registerVehicle(command);
    }

}
