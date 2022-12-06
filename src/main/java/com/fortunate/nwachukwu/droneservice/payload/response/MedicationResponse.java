package com.fortunate.nwachukwu.droneservice.payload.response;

import com.fortunate.nwachukwu.droneservice.model.Medication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicationResponse {
    private String name;
    private double weight;
    private String code;
    private String image;

    public static MedicationResponse fromMedication(Medication medication) {
        return MedicationResponse.builder()
                .name(medication.getName())
                .weight(medication.getWeight())
                .code(medication.getCode())
                .image(medication.getImage())
                .build();
    }
}
