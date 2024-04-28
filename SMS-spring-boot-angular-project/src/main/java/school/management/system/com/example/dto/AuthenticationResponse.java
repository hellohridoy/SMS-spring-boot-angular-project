package school.management.system.com.example.dto;

import lombok.Data;


public class AuthenticationResponse {
    private String jwtToken;

    public AuthenticationResponse(String jwtToken) {

    }
}
