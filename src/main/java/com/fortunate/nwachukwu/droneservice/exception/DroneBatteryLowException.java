package com.fortunate.nwachukwu.droneservice.exception;


public class DroneBatteryLowException extends RuntimeException {
    public DroneBatteryLowException(String message)  {
        super(message);
    }
}


