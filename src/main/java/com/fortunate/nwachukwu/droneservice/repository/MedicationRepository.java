package com.fortunate.nwachukwu.droneservice.repository;

import com.fortunate.nwachukwu.droneservice.model.Medication;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;
import com.fortunate.nwachukwu.droneservice.payload.response.MedicationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findByCode(String medicineCode);
    Optional<Medication> findByName(String medicineName);


    List<Medication> findAllByCodeIn(Set<String> codes);
}
