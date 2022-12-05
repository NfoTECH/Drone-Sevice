package com.fortunate.nwachukwu.droneservice.service;

import com.fortunate.nwachukwu.droneservice.model.Drone;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneState;
import com.fortunate.nwachukwu.droneservice.payload.request.DroneRequest;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;

import java.util.List;

public interface DroneService {
    Drone registerDrone(DroneRequest droneRequest);
    List<Drone> listAvailableDronesForLoading();

}
