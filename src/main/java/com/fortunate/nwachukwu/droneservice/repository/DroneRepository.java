package com.fortunate.nwachukwu.droneservice.repository;

import com.fortunate.nwachukwu.droneservice.model.Drone;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    Optional<Drone> findDroneBySerialNumber(String serialNumber);
    Optional<Drone> findDroneByState(DroneState state);

    List<Drone> findDronesByState(DroneState state);
}
