package com.fortunate.nwachukwu.droneservice.service.implementation;

import com.fortunate.nwachukwu.droneservice.exception.DroneAlreadyRegisteredException;
import com.fortunate.nwachukwu.droneservice.model.Drone;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneState;
import com.fortunate.nwachukwu.droneservice.payload.request.DroneRequest;
import com.fortunate.nwachukwu.droneservice.repository.DroneRepository;
import com.fortunate.nwachukwu.droneservice.service.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DroneServiceImplementation implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Drone registerDrone(DroneRequest droneRequest) {
        Optional<Drone> registeredDrone =
                droneRepository.findDroneBySerialNumber(droneRequest.getSerialNumber());
        if (registeredDrone.isEmpty()) {
            Drone drone = Drone.builder()
                    .model(droneRequest.getModel())
                    .weightLimit(droneRequest.getWeightLimit())
                    .batteryCapacity(droneRequest.getBatteryCapacity())
                    .serialNumber(droneRequest.getSerialNumber())
                    .state(DroneState.IDLE)
                    .build();
            return droneRepository.save(drone);
        } else {
            throw new DroneAlreadyRegisteredException("Drone with serialNumber: "
                    + droneRequest.getSerialNumber()
                    + " is already registered");
        }
    }

    @Override
    public List<Drone> listAvailableDronesForLoading() {
        return droneRepository.findDronesByState(DroneState.IDLE)
                .stream()
                .filter(drone -> drone.getBatteryCapacity() >= 25f)
                .collect(Collectors.toList());
    }
}
