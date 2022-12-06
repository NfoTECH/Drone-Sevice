package com.fortunate.nwachukwu.droneservice.exception;


public class WeightLimitExceededException extends RuntimeException {
    public WeightLimitExceededException(String message) {
        super(message);
    }
}
