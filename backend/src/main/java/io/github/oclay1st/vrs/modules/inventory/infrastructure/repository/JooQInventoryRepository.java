package io.github.oclay1st.vrs.modules.inventory.infrastructure.repository;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.oclay1st.vrs.autogenerated.jooq.tables.ArchivedVehicle.ARCHIVED_VEHICLE;
import static io.github.oclay1st.vrs.autogenerated.jooq.tables.Vehicle.VEHICLE;

import io.github.oclay1st.vrs.autogenerated.jooq.tables.records.ArchivedVehicleRecord;
import io.github.oclay1st.vrs.autogenerated.jooq.tables.records.VehicleRecord;
import io.github.oclay1st.vrs.modules.common.Page;
import io.github.oclay1st.vrs.modules.common.PageCriteria;
import io.github.oclay1st.vrs.modules.inventory.domain.command.RegisterVehicleCommand;
import io.github.oclay1st.vrs.modules.inventory.domain.criteria.VehicleCriteria;
import io.github.oclay1st.vrs.modules.inventory.domain.model.GasFuelType;
import io.github.oclay1st.vrs.modules.inventory.domain.model.VehicleType;
import io.github.oclay1st.vrs.modules.inventory.domain.projection.VehicleDetailsView;
import io.github.oclay1st.vrs.modules.inventory.domain.projection.VehicleView;
import io.github.oclay1st.vrs.modules.inventory.domain.repository.InventoryRepository;

@Repository
public class JooQInventoryRepository implements InventoryRepository {

    private final DSLContext client;

    public JooQInventoryRepository(DSLContext client) {
        this.client = client;
    }

    @Override
    public void registerVehicle(RegisterVehicleCommand command) {
        VehicleRecord vehicleRecord = client.newRecord(VEHICLE);
        vehicleRecord.setVin(command.getVin());
        vehicleRecord.setLicensePlate(command.getLicensePlate());
        vehicleRecord.setType(command.getType().name());
        if (command.getType() == VehicleType.DIESEL) {
            vehicleRecord.setDieselInjectionPumpType(command.getDieselInjectionPumpType().name());
        }
        if (command.getType() == VehicleType.GASOLINE) {
            String gasFuelTypes = command.getGasFuelTypes().stream()
                    .map(GasFuelType::name)
                    .collect(Collectors.joining(","));
            vehicleRecord.setGasFuelTypes(gasFuelTypes);
        }
        if (command.getType() == VehicleType.ELECTRIC) {
            vehicleRecord.setBatteryType(command.getBatteryType().name());
            vehicleRecord.setBatteryVoltage(command.getBatteryVoltage());
            vehicleRecord.setBatteryAmperage(command.getBatteryAmperage());
            vehicleRecord.setConvertible(command.isConvertible());
            if (command.isConvertible() != null && command.isConvertible()) {
                vehicleRecord.setConvertibleLicensePlate(command.getConvertibleLicensePlate());
                String convertibleGasFuelTypes = command.getConvertibleGasFuelTypes().stream()
                        .map(GasFuelType::name)
                        .collect(Collectors.joining(","));
                vehicleRecord.setConvertibleGasFuelTypes(convertibleGasFuelTypes);
            }
        }
        vehicleRecord.setUserId(command.getUserId());
        vehicleRecord.setCreatedAt(command.getCreatedAt());
        vehicleRecord.setUpdatedAt(command.getUpdatedAt());
        vehicleRecord.store();
    }

    @Override
    public boolean existsByVIN(Long vin) {
        return client.fetchExists(VEHICLE, VEHICLE.VIN.eq(vin));
    }

    @Override
    public boolean existsByLicensePlate(String licensePlate) {
        return client.fetchExists(VEHICLE, VEHICLE.LICENSE_PLATE.eq(licensePlate));
    }

    @Override
    public Page<VehicleView> findByCriteria(VehicleCriteria criteria, PageCriteria pageCriteria) {
        Condition condition = parseCriteria(criteria);
        int totalItems = client.fetchCount(VEHICLE, condition);
        if (totalItems == 0) {
            return Page.empty(pageCriteria.page(), pageCriteria.size());
        }
        List<VehicleView> items = client
                .select(VEHICLE.VIN, VEHICLE.LICENSE_PLATE, VEHICLE.TYPE, VEHICLE.CREATED_AT, VEHICLE.UPDATED_AT)
                .from(VEHICLE)
                .where(condition)
                .offset(pageCriteria.offset())
                .limit(pageCriteria.size())
                .fetchInto(VehicleView.class);
        return new Page<>(items, pageCriteria.page(), pageCriteria.size(), totalItems);
    }

    private Condition parseCriteria(VehicleCriteria criteria) {
        Condition condition = DSL.noCondition();
        if (criteria.vin() != null) {
            condition = condition.and(VEHICLE.VIN.eq(criteria.vin()));
        }
        if (criteria.licensePlate() != null) {
            condition = condition.and(VEHICLE.LICENSE_PLATE.likeIgnoreCase("%" + criteria.licensePlate() + "%"));
        }
        if (criteria.type() != null) {
            condition = condition.and(VEHICLE.TYPE.eq(criteria.type().name()));
            if (criteria.type() == VehicleType.DIESEL && criteria.dieselInjectionPumpType() != null) {
                condition = condition
                        .and(VEHICLE.DIESEL_INJECTION_PUMP_TYPE.eq(criteria.dieselInjectionPumpType().name()));
            }
            if (criteria.type() == VehicleType.GASOLINE && criteria.gasFuelTypes() != null) {
                condition = condition.and(VEHICLE.GAS_FUEL_TYPES.eq(criteria.gasFuelTypes().get(0).name()));
            }
            if (criteria.type() == VehicleType.ELECTRIC && criteria.batteryType() != null) {
                condition = condition.and(VEHICLE.BATTERY_TYPE.eq(criteria.batteryType().name()));
            }
        }
        if (criteria.userId() != null) {
            condition = condition.and(VEHICLE.USER_ID.eq(criteria.userId()));
        }
        return condition;
    }

    @Override
    public void archiveVehicle(Long vin) {
        VehicleRecord vehicleRecord = client.fetchSingle(VEHICLE, VEHICLE.VIN.eq(vin));
        ArchivedVehicleRecord archivedVehicleRecord = client.newRecord(ARCHIVED_VEHICLE);
        archivedVehicleRecord.setVin(vehicleRecord.getVin());
        archivedVehicleRecord.setLicensePlate(vehicleRecord.getLicensePlate());
        archivedVehicleRecord.setType(vehicleRecord.getType());
        archivedVehicleRecord.setDieselInjectionPumpType(vehicleRecord.getDieselInjectionPumpType());
        archivedVehicleRecord.setGasFuelTypes(vehicleRecord.getGasFuelTypes());
        archivedVehicleRecord.setBatteryType(vehicleRecord.getBatteryType());
        archivedVehicleRecord.setBatteryVoltage(vehicleRecord.getBatteryVoltage());
        archivedVehicleRecord.setBatteryAmperage(vehicleRecord.getBatteryAmperage());
        archivedVehicleRecord.setRegisteredAt(vehicleRecord.getCreatedAt());
        archivedVehicleRecord.setUserId(vehicleRecord.getUserId());
        archivedVehicleRecord.setArchivedAt(LocalDateTime.now());
        archivedVehicleRecord.store();
        vehicleRecord.delete();
    }

    @Override
    public Optional<VehicleDetailsView> findDetailsByVIN(Long vin) {
        Function<String, List<GasFuelType>> converter = value -> Optional.ofNullable(value)
                .map(v -> Arrays.stream(v.split(",")).map(GasFuelType::valueOf).collect(Collectors.toList()))
                .orElse(null);
        return client
                .select(VEHICLE.VIN, VEHICLE.LICENSE_PLATE, VEHICLE.TYPE, VEHICLE.DIESEL_INJECTION_PUMP_TYPE,
                        VEHICLE.GAS_FUEL_TYPES.convertFrom(List.class, converter), VEHICLE.BATTERY_TYPE,
                        VEHICLE.BATTERY_VOLTAGE, VEHICLE.BATTERY_AMPERAGE, VEHICLE.CONVERTIBLE,
                        VEHICLE.CONVERTIBLE_LICENSE_PLATE,
                        VEHICLE.CONVERTIBLE_GAS_FUEL_TYPES.convertFrom(List.class, converter), VEHICLE.CREATED_AT,
                        VEHICLE.UPDATED_AT)
                .from(VEHICLE)
                .where(VEHICLE.VIN.eq(vin))
                .fetchOptionalInto(VehicleDetailsView.class);
    }

}
