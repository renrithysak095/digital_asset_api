package com.example.digital_asset_project.feature.authentication.controller;

import com.example.digital_asset_project.feature.authentication.auth.*;
import com.example.digital_asset_project.feature.authentication.service.AuthService;
import com.example.digital_asset_project.jwt.JwtTokenUtils;
import com.example.digital_asset_project.model.request.MemberRequest;
import com.example.digital_asset_project.model.response.ApiResponse;
import com.example.digital_asset_project.model.response.MemberCreated;
import com.example.digital_asset_project.model.response.MemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    //Register
    @PostMapping("")
    @Operation(summary = "member registration")
    public ResponseEntity<ApiResponse<MemberCreated>> addingUser(@Valid @RequestBody MemberRequest request) throws MessagingException {
        return new ResponseEntity<>(new ApiResponse<>(
                "adding member successfully",
                authService.adding(request),
                LocalDateTime.now()
        ), HttpStatus.CREATED);
    }

    // Test method
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("test")
    public String testte(){
        return "";
    }

    //Login
//    @PostMapping("/login")
//    @Operation(summary = "auth login")
//    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest){
//
//        try {
//            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid email or password");
//        }
//
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//        final UserDetails userDetails = authService
//                    .loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtTokenUtils.generateToken(userDetails);
//
//        String loginUsername = authenticationRequest.getUsername();
//        String equalsUsername = authService.getUserName(loginUsername);
//
//        if (loginUsername.equals(equalsUsername)) {
//            Integer userId = authService.getUserId(equalsUsername);
//            authenticationResponse.setUserId(userId);
//        }
//
//        authenticationResponse.setToken(token);
//        authenticationResponse.setUsername(authenticationRequest.getUsername());
//        ApiResponse<Object> responseMessagesObjects = new ApiResponse<>();
//        responseMessagesObjects.setPayload(authenticationResponse);
//        return ResponseEntity.ok().body(responseMessagesObjects);
//    }
//
//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }

}

