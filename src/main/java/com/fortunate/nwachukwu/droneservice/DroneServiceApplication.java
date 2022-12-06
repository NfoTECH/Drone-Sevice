package com.fortunate.nwachukwu.droneservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortunate.nwachukwu.droneservice.model.Drone;
import com.fortunate.nwachukwu.droneservice.model.Medication;
import com.fortunate.nwachukwu.droneservice.repository.DroneRepository;
import com.fortunate.nwachukwu.droneservice.repository.MedicationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class DroneServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroneServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Drone>> typeReference = new TypeReference<>() {};
            TypeReference<List<Medication>> medicationTypeReference = new TypeReference<>(){};
            InputStream droneInputStream = TypeReference.class.getResourceAsStream("/drone.json");
            InputStream medicationInputStream = TypeReference.class.getResourceAsStream("/medication.json");
            try {
                List<Drone> drones = mapper.readValue(droneInputStream, typeReference);
                List<Medication> medications = mapper.readValue(medicationInputStream, medicationTypeReference);
                droneRepository.saveAll(drones);
                medicationRepository.saveAll(medications);
            } catch (IOException e) {
                throw new RuntimeException("Error occurred while reading from file");
            }
        };
    }
}
