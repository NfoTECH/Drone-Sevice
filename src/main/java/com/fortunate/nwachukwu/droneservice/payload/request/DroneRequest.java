package com.fortunate.nwachukwu.droneservice.payload.request;

import com.fortunate.nwachukwu.droneservice.model.Medication;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneModel;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class DroneRequest {

    @NotBlank(message = "Drone serialNumber is required")
    @Size(max = 100, message = "Serial number is not more than 100 characters")
    private String serialNumber;

    @NotNull(message = "Drone model is required")
    private DroneModel model;

    @NotNull(message = " Drone weight limit is required")
    @DecimalMax(value = "500", message =" Drone weight limit is {value} grams maximum")
    private Double weightLimit;

    @NotNull(message = "Drone battery capacity is required")
    @DecimalMax(value = "100")
    private Double batteryCapacity;

    private DroneState state;

    private List<Medication> medication;
}
