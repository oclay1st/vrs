package io.github.oclay1st.vrs.modules.inventory.domain.projection;

import java.time.LocalDateTime;

public record VehicleView(Long vin, String licensePlate, String type, LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
