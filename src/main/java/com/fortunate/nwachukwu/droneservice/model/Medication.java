package com.fortunate.nwachukwu.droneservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "medications")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medication extends BaseModel {

    private String name;

    private Double weight;

    private String code;

    private String image;
    @JsonBackReference
    @ManyToOne
    private Drone drone;
}
