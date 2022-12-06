package com.fortunate.nwachukwu.droneservice.payload.response;

import com.fortunate.nwachukwu.droneservice.model.Drone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneResponse {
    private String serialNumber;
    private Double batteryLevel;

    public static  DroneResponse fromDrone(Drone drone) {
        return DroneResponse.builder()
                .serialNumber(drone.getSerialNumber())
                .batteryLevel(drone.getBatteryCapacity())
                .build();
    }
}
