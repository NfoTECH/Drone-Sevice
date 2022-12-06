package com.fortunate.nwachukwu.droneservice.service.implementation;

import com.fortunate.nwachukwu.droneservice.exception.*;
import com.fortunate.nwachukwu.droneservice.model.Delivery;
import com.fortunate.nwachukwu.droneservice.model.Drone;
import com.fortunate.nwachukwu.droneservice.model.Medication;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneState;
import com.fortunate.nwachukwu.droneservice.payload.request.DroneRequest;
import com.fortunate.nwachukwu.droneservice.payload.request.LoadingRequest;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;
import com.fortunate.nwachukwu.droneservice.payload.response.DeliveryResponse;
import com.fortunate.nwachukwu.droneservice.payload.response.DroneResponse;
import com.fortunate.nwachukwu.droneservice.payload.response.MedicationResponse;
import com.fortunate.nwachukwu.droneservice.repository.DeliveryRepository;
import com.fortunate.nwachukwu.droneservice.repository.DroneRepository;
import com.fortunate.nwachukwu.droneservice.repository.MedicationRepository;
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
    private final MedicationRepository medicationRepository;
    private final DeliveryRepository deliveryRepository;

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


    @Override
    public DeliveryResponse loadDroneWithMedication(String serialNumber, LoadingRequest medicationCodes) {

        Drone drone = droneRepository.findDroneBySerialNumberAndState(serialNumber, DroneState.IDLE).orElse(null);
        if (drone.getBatteryCapacity() < 25f) {
            throw new DroneBatteryLowException("Drone with serialNumber: "
                    + serialNumber
                    + " is not available for loading");
        }
        List<Medication> medicationList = medicationCodes.getMedicationCodes().stream().map(a-> Medication.builder().code(a).build()).collect(Collectors.toList());
        medicationList = medicationRepository.findAllByCodeIn(medicationCodes.getMedicationCodes());

        if (medicationList.size() == 0){
            throw new InvalidLoadRequestException("No medication with the code(s) provided");
        }
        double sum = medicationList.stream().mapToDouble(Medication::getWeight).sum();
        assert drone != null;
        if(sum <= drone.getWeightLimit()){
            drone.setState(DroneState.LOADED);
            droneRepository.save(drone);
            Delivery delivery = Delivery.builder()
                    .drone(drone)
                    .medications(medicationList)
                    .isDelivered(false)
                    .build();
            return DeliveryResponse.fromDelivery(deliveryRepository.save(delivery));
        }else{
            throw new WeightLimitExceededException("The total weight of the medication(s) exceeds the drone's weight limit");
        }
    }

    @Override
    public DroneResponse checkDroneBatteryLevel(String serialNumber) {
        Drone drone = droneRepository.findDroneBySerialNumber(serialNumber)
                .orElseThrow(() -> new NoAvailableDroneException("Drone with input serialNumber: "
                        + serialNumber
                        + " not found"));

        return DroneResponse.builder()
                .serialNumber(drone.getSerialNumber())
                .batteryLevel(drone.getBatteryCapacity())
                .build();
    }

    @Override
    public DeliveryResponse checkLoadedMedicationForADrone(String serialNumber) {
        Drone drone = droneRepository.findDroneBySerialNumberAndState(serialNumber, DroneState.LOADED)
                .orElseThrow(() -> new NoAvailableDroneException
                        ("Drone with serial number: " + serialNumber + " is not loaded with medication"));
        return  DeliveryResponse.fromDelivery(deliveryRepository.findDeliveryByDroneAndIsDelivered(drone, false));
    }
}