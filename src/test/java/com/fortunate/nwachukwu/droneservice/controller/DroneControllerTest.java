package com.fortunate.nwachukwu.droneservice.controller;

import com.fortunate.nwachukwu.droneservice.model.Drone;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneModel;
import com.fortunate.nwachukwu.droneservice.model.enums.DroneState;
import com.fortunate.nwachukwu.droneservice.payload.request.DroneRequest;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;
import com.fortunate.nwachukwu.droneservice.service.DroneService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class DroneControllerTest {

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    @Test
    @DisplayName("Should return a list of available drones")
    void getAvailableDroneShouldReturnListOfAvailableDrones() {
        when(droneService.listAvailableDronesForLoading()).thenReturn(null);
        ResponseEntity<ApiResponse> response = droneController.getAvailableDrone();
        verify(droneService, times(1)).listAvailableDronesForLoading();
    }

    @Test
    @DisplayName("Should return a success response when the drone is registered")
    void registerDroneWhenDroneIsRegisteredThenReturnSuccessResponse() {
        DroneRequest droneRequest =
                new DroneRequest("123", DroneModel.LIGHTWEIGHT, 100.0, 50.0, DroneState.IDLE, null);
        Drone drone = new Drone("123", DroneModel.LIGHTWEIGHT, 100.0, 50.0, DroneState.IDLE);
        when(droneService.registerDrone(droneRequest)).thenReturn(drone);

        ResponseEntity<ApiResponse> response = droneController.registerDrone(droneRequest);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Registered successfully", response.getBody().getMessage());
        assertTrue(response.getBody().isStatus());
        assertEquals(drone, response.getBody().getData());
    }

    @Test
    @DisplayName("Should return a bad request response when the drone is not registered")
    void registerDroneWhenDroneIsNotRegisteredThenReturnBadRequestResponse() {
        DroneRequest droneRequest =
                new DroneRequest(
                        "123", DroneModel.LIGHTWEIGHT, 100.0, 100.0, DroneState.IDLE, null);
        when(droneService.registerDrone(droneRequest)).thenReturn(null);
        ResponseEntity<ApiResponse> response = droneController.registerDrone(droneRequest);
        assertEquals(response.getStatusCode(), OK);
    }
}