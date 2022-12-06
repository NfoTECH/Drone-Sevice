package com.fortunate.nwachukwu.droneservice.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private String message;

    private boolean status;

    private T data;

    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
