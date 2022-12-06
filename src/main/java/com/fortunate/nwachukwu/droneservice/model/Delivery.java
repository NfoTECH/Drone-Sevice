package com.fortunate.nwachukwu.droneservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "deliveries")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery extends BaseModel {
    @OneToOne
    private Drone drone;
    @OneToMany
    private List<Medication> medications;
    private Boolean isDelivered = false;
}
