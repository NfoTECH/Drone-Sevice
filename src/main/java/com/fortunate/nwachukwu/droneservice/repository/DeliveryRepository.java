package com.fortunate.nwachukwu.droneservice.repository;

import com.fortunate.nwachukwu.droneservice.model.Delivery;
import com.fortunate.nwachukwu.droneservice.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findDeliveryByDroneAndIsDelivered(Drone drone, Boolean isDelivered);
}
