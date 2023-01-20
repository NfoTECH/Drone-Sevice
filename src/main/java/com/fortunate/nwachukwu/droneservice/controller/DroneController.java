package com.fortunate.nwachukwu.droneservice.controller;

import com.fortunate.nwachukwu.droneservice.model.Medication;
import com.fortunate.nwachukwu.droneservice.payload.request.DroneRequest;
import com.fortunate.nwachukwu.droneservice.payload.request.LoadingRequest;
import com.fortunate.nwachukwu.droneservice.payload.request.MedicationRequest;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;
import com.fortunate.nwachukwu.droneservice.service.DroneService;
import com.fortunate.nwachukwu.droneservice.util.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/drone")
public class DroneController {

    private final DroneService droneService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerDrone(@RequestBody @Valid DroneRequest droneRequest){
        return Responder.success(droneService.registerDrone(droneRequest));
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse> getAvailableDrone(){
        return Responder.found(droneService.listAvailableDronesForLoading());
    }

    @PostMapping("/load-medication/{drone-serial}")
    public ResponseEntity<ApiResponse> loadADroneWithMedication(
            @PathVariable("drone-serial") String serialNumber ,
            @RequestBody LoadingRequest medicationCodes) {
        return Responder.success(droneService.loadDroneWithMedication(serialNumber, medicationCodes));
    }

    @GetMapping("/check-battery/{drone-serial}")
    public ResponseEntity<ApiResponse> checkDroneBatteryLevel(@PathVariable("drone-serial") String serialNumber) {
        return Responder.found(droneService.checkDroneBatteryLevel(serialNumber));
    }

    @GetMapping("/check-loaded-medication/{drone-serial}")
    public ResponseEntity<ApiResponse> getLoadedMedication(@PathVariable("drone-serial") String serialNumber) {
        return Responder.found(droneService.checkLoadedMedicationForADrone(serialNumber));
    }
}
