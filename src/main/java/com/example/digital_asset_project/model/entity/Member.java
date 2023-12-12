package com.example.digital_asset_project.model.entity;

import com.example.digital_asset_project.config.ValidationConfig;
import com.example.digital_asset_project.model.response.MemberCreated;
import com.example.digital_asset_project.model.response.MemberResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @NotEmpty
    private String email;
    @NotBlank(message = ValidationConfig.PASSWORD_REQUIRED_MESSAGE)
    @Size(min = ValidationConfig.PASSWORD_VALIDATION_MIN, message = ValidationConfig.PASSWORD_RESPONSE_MESSAGE)
    @Pattern(regexp = ValidationConfig.PASSWORD_VALIDATION_REG, message = ValidationConfig.PASSWORD_RESPONSE_REG_MESSAGE)
    private String password;
    private Integer otp;

    public MemberResponse toDto(String token){
        return new MemberResponse(this.id,this.email,token);
    }

    public MemberCreated created(){
        return new MemberCreated(this.id,this.email);
    }
}
