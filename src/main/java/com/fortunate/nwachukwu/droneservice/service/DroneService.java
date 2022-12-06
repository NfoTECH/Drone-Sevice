package com.fortunate.nwachukwu.droneservice.service;

import com.fortunate.nwachukwu.droneservice.model.Delivery;
import com.fortunate.nwachukwu.droneservice.model.Drone;
import com.fortunate.nwachukwu.droneservice.model.Medication;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneState;
import com.fortunate.nwachukwu.droneservice.payload.request.DroneRequest;
import com.fortunate.nwachukwu.droneservice.payload.request.LoadingRequest;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;
import com.fortunate.nwachukwu.droneservice.payload.response.DeliveryResponse;
import com.fortunate.nwachukwu.droneservice.payload.response.DroneResponse;

import java.util.List;
import java.util.Set;

public interface DroneService {
    Drone registerDrone(DroneRequest droneRequest);
    List<Drone> listAvailableDronesForLoading();
    DeliveryResponse loadDroneWithMedication(String serialNumber, LoadingRequest medicationCodes);

    DroneResponse checkDroneBatteryLevel(String serialNumber);

    DeliveryResponse checkLoadedMedicationForADrone(String serialNumber);

}
