package com.fortunate.nwachukwu.droneservice.util;

import com.fortunate.nwachukwu.droneservice.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.http.HttpStatus.*;

public class Responder {

    public static ResponseEntity<ApiResponse> success(Object response){
        return new ResponseEntity<>(new ApiResponse("Registered successfully", true, response), OK);
    }

    public static ResponseEntity<ApiResponse> found (Object response) {
        return new ResponseEntity<>(new ApiResponse("Retrieved successfully", true, response), FOUND);
    }

    public static ResponseEntity<ApiResponse> conflict (Object response) {
        return new ResponseEntity<>(new ApiResponse("Already exist", false), CONFLICT);
    }

    public static ResponseEntity<ApiResponse> notFound (Object response) {
        return new ResponseEntity<>(new ApiResponse("Not found", false), NOT_FOUND);
    }

    public static ResponseEntity<ApiResponse> notAllowed (Object response) {
        return new ResponseEntity<>(new ApiResponse<>("Not allowed", false), FORBIDDEN);
    }
}
