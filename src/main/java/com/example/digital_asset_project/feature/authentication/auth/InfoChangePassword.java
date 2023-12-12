package com.example.digital_asset_project.feature.authentication.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"username","currentPassword","newPassword"})
public class InfoChangePassword {
    @NotBlank(message = "Password is required")
    private String currentPassword;
    @NotBlank(message = "Password is required")
    private String newPassword;

    @NotBlank(message = "Password is required")
    private String confirmPassword;

}
