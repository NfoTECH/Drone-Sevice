package com.fortunate.nwachukwu.droneservice.controller;

import com.fortunate.nwachukwu.droneservice.exception.*;
import com.fortunate.nwachukwu.droneservice.util.Responder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DroneAlreadyRegisteredException.class)
    public ResponseEntity<?> droneAlreadyRegisteredException(DroneAlreadyRegisteredException exception) {
        return Responder.conflict(exception);
    }

    @ExceptionHandler(DroneBatteryLowException.class)
    public ResponseEntity<?> droneBatteryLowException(DroneBatteryLowException exception) {
        return Responder.notAllowed(exception);
    }

    @ExceptionHandler(MaximumWeightExceededException.class)
    public ResponseEntity<?> maximumWeightExceededException(MaximumWeightExceededException exception) {
        return Responder.notAllowed(exception);
    }

    @ExceptionHandler(MedicationAlreadyExistException.class)
    public ResponseEntity<?> medicationAlreadyExistException(MedicationAlreadyExistException exception) {
        return Responder.conflict(exception);
    }

    @ExceptionHandler(NoAvailableDroneException.class)
    public ResponseEntity<?> noAvailableDroneException(NoAvailableDroneException exception) {
        return Responder.notFound(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
}
