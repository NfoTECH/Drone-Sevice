package com.fortunate.nwachukwu.droneservice.exception;


public class DroneAlreadyRegisteredException extends RuntimeException {
    public DroneAlreadyRegisteredException(String message) {
        super(message);
    }
}
