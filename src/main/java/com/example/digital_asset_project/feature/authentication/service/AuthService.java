package com.example.digital_asset_project.feature.authentication.service;


import com.example.digital_asset_project.feature.authentication.auth.InfoChangePassword;
import com.example.digital_asset_project.feature.authentication.auth.MyUserDTO;
import com.example.digital_asset_project.feature.authentication.auth.MyUserRequest;
import com.example.digital_asset_project.model.request.MemberRequest;
import com.example.digital_asset_project.model.response.MemberCreated;
import com.example.digital_asset_project.model.response.MemberResponse;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface AuthService extends UserDetailsService {
    MemberCreated adding(MemberRequest request) throws MessagingException;

//    String getUserName(String loginUsername);
//
//    Integer getUserId(String equalsUsername);
//
//    InfoChangePassword changePassword(Integer id, InfoChangePassword password);
}
