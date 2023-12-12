package com.example.digital_asset_project.feature.authentication.auth;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyUserDTO{
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Integer userId;
    String username;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String userRole;
}
