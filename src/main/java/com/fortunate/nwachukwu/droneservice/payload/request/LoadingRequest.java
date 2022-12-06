package com.fortunate.nwachukwu.droneservice.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadingRequest {
    Set<String> medicationCodes;
}
