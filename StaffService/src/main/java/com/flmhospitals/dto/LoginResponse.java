package com.flmhospitals.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private long expiresInMillis;
    private String role;
    private String staffId;
    private boolean requirePasswordReset;
}

