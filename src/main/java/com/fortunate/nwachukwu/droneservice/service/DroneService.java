package com.fortunate.nwachukwu.droneservice.service;

import com.fortunate.nwachukwu.droneservice.model.Drone;
import com.fortunate.nwachukwu.droneservice.payload.request.DroneRequest;
import com.fortunate.nwachukwu.droneservice.payload.request.LoadingRequest;
import com.fortunate.nwachukwu.droneservice.payload.response.DeliveryResponse;
import com.fortunate.nwachukwu.droneservice.payload.response.DroneResponse;

import java.util.List;

public interface DroneService {
    Drone registerDrone(DroneRequest droneRequest);
    List<Drone> listAvailableDronesForLoading();
    DeliveryResponse loadDroneWithMedication(String serialNumber, LoadingRequest medicationCodes);

    DroneResponse checkDroneBatteryLevel(String serialNumber);

    DeliveryResponse checkLoadedMedicationForADrone(String serialNumber);

}
