package com.fortunate.nwachukwu.droneservice.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fortunate.nwachukwu.droneservice.model.Delivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class DeliveryResponse {
    private DroneResponse drone;
    private List<MedicationResponse> medications;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss a")
    private LocalDateTime createAt;

    public static DeliveryResponse fromDelivery(Delivery delivery) {
        return DeliveryResponse.builder()
                .drone(DroneResponse.fromDrone(delivery.getDrone()))
                .medications(delivery.getMedications().stream().map(MedicationResponse::fromMedication).collect(Collectors.toList()))
                .createAt(delivery.getCreatedAt())
                .build();
    }
}
