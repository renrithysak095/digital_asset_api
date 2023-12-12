package com.example.digital_asset_project.feature.authentication.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"username","password"})
public class MyUserRequest{
    @NotBlank(message = "Password is required")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_=+{};:,<.>])(?!.*\\s).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and be at least 8 characters long.")
    String password;
    String username;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String userRole;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime createdAt = LocalDateTime.now();
}
