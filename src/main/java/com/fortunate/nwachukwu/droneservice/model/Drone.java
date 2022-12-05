package com.fortunate.nwachukwu.droneservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneModel;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneState;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "drones")
@NoArgsConstructor
public class Drone extends BaseModel {

    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    private Double weightLimit;

    private Double batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

    @JsonManagedReference
    @OneToMany(mappedBy = "drone")
    private List<Medication> medication;
}
