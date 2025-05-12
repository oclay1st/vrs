package io.github.oclay1st.vrs.modules.inventory.domain.repository;

import java.util.Optional;

import io.github.oclay1st.vrs.modules.common.Page;
import io.github.oclay1st.vrs.modules.common.PageCriteria;
import io.github.oclay1st.vrs.modules.inventory.domain.command.RegisterVehicleCommand;
import io.github.oclay1st.vrs.modules.inventory.domain.criteria.VehicleCriteria;
import io.github.oclay1st.vrs.modules.inventory.domain.projection.VehicleDetailsView;
import io.github.oclay1st.vrs.modules.inventory.domain.projection.VehicleView;

public interface InventoryRepository {

    void registerVehicle(RegisterVehicleCommand command);

    boolean existsByVIN(Long vin);

    boolean existsByLicensePlate(String licensePlate);

    Page<VehicleView> findByCriteria(VehicleCriteria vehicleCriteria, PageCriteria pageCriteria);

    void archiveVehicle(Long vin);

    Optional<VehicleDetailsView> findDetailsByVIN(Long vin);

}
