package com.fortunate.nwachukwu.droneservice.util;

import com.fortunate.nwachukwu.droneservice.exception.*;
import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.*;

public class Responder {

    public static ResponseEntity<ApiResponse> success(Object response){
        return new ResponseEntity<>(new ApiResponse("Successful", true, response), OK);
    }

    public static ResponseEntity<ApiResponse> found (Object response) {
        return new ResponseEntity<>(new ApiResponse("Retrieved successfully", true, response), FOUND);
    }

public static ResponseEntity<ApiResponse> conflict (Object response) {
    return new ResponseEntity<>(new ApiResponse(((DroneAlreadyRegisteredException)response).getMessage(), false), CONFLICT);
}

    public static ResponseEntity<ApiResponse> notFound (Object response) {
        return new ResponseEntity<>(new ApiResponse(((NoAvailableDroneException)response).getMessage(), false), NOT_FOUND);
    }

    public static ResponseEntity<ApiResponse> overWeight (Object response) {
        return new ResponseEntity<>(new ApiResponse<>(((WeightLimitExceededException)response).getMessage(), false), FORBIDDEN);
    }

    public static ResponseEntity<ApiResponse> lowBattery (Object response) {
        return new ResponseEntity<>(new ApiResponse<>(((DroneBatteryLowException)response).getMessage(), false), FORBIDDEN);
    }

    public static ResponseEntity<ApiResponse> badRequest (Object response) {
        return new ResponseEntity<>(new ApiResponse<>(((InvalidLoadRequestException)response).getMessage(), false), BAD_REQUEST);
    }
}
