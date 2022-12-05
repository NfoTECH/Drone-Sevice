package com.fortunate.nwachukwu.droneservice.service;

import com.fortunate.nwachukwu.droneservice.payload.request.MedicationRequest;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;

public interface MedicationService {
    ApiResponse<?> medicationDetails(MedicationRequest medicationRequest);
    ApiResponse<?> listOfMedications();
}
