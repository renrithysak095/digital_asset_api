package com.example.digital_asset_project.feature.authentication.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse{
    Integer userId;
    String username;
    String token;
}
