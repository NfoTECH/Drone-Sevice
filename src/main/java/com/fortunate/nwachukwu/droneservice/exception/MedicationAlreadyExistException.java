package com.fortunate.nwachukwu.droneservice.exception;


public class MedicationAlreadyExistException extends RuntimeException {
    public MedicationAlreadyExistException(String message) {
        super (message);
    }
}
