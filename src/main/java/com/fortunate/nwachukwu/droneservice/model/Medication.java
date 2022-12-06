package com.fortunate.nwachukwu.droneservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


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
}
