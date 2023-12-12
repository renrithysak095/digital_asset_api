package com.example.digital_asset_project.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse{
    private UUID id;
    private String email;
    private String token;
}
