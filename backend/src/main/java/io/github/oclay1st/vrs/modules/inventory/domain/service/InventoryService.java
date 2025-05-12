package io.github.oclay1st.vrs.modules.inventory.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.oclay1st.vrs.modules.common.Page;
import io.github.oclay1st.vrs.modules.common.PageCriteria;
import io.github.oclay1st.vrs.modules.common.exceptions.ResourceNotFoundException;
import io.github.oclay1st.vrs.modules.common.exceptions.ValidationException;
import io.github.oclay1st.vrs.modules.inventory.domain.command.RegisterVehicleCommand;
import io.github.oclay1st.vrs.modules.inventory.domain.criteria.VehicleCriteria;
import io.github.oclay1st.vrs.modules.inventory.domain.projection.VehicleDetailsView;
import io.github.oclay1st.vrs.modules.inventory.domain.projection.VehicleView;
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

    /**
     * Find all the vehicles given a criteria
     * 
     * @param criteria     a {@link VehicleCriteria}
     * @param pageCriteria a {@link PageCriteria}
     * @return a {@link Page<VehicleView>}
     */
    public Page<VehicleView> findByCriteria(VehicleCriteria vehicleCriteria, PageCriteria pageCriteria) {
        return inventoryRepository.findByCriteria(vehicleCriteria, pageCriteria);
    }

    /**
     * Archive a vehicle
     * 
     * @param vin the value of the identification number
     */
    @Transactional
    public void archiveVehicle(Long vin) {
        if (!inventoryRepository.existsByVIN(vin)) {
            throw new ResourceNotFoundException("Vehicle not found with the given VIN: " + vin);
        }
        inventoryRepository.archiveVehicle(vin);
    }

    /**
     * Find vehicle detail
     * 
     * @param vin the identification number
     * @return a {@lin VehicleDetailsView} instance
     */
    public VehicleDetailsView findDetailsByVIN(Long vin) {
        return inventoryRepository.findDetailsByVIN(vin)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with the given VIN: " + vin));
    }

}
