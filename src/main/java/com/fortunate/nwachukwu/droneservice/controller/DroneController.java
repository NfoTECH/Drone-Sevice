package com.fortunate.nwachukwu.droneservice.controller;

import com.fortunate.nwachukwu.droneservice.payload.request.DroneRequest;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;
import com.fortunate.nwachukwu.droneservice.service.DroneService;
import com.fortunate.nwachukwu.droneservice.util.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

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
}
