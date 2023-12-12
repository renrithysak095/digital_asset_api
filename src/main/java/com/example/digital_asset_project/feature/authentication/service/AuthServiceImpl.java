package com.example.digital_asset_project.feature.authentication.service;

import com.example.digital_asset_project.config.ValidationConfig;
import com.example.digital_asset_project.exception.NotFoundExceptionClass;
import com.example.digital_asset_project.feature.authentication.auth.*;
//import com.example.digital_asset_project.feature.authentication.repository.AuthRepository;
import com.example.digital_asset_project.feature.authentication.repository.AuthRepository;
import com.example.digital_asset_project.mail.EmailServiceImpl;
import com.example.digital_asset_project.model.request.MemberRequest;
import com.example.digital_asset_project.model.response.MemberCreated;
import com.example.digital_asset_project.model.response.MemberResponse;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;

    public AuthServiceImpl(AuthRepository authRepository, PasswordEncoder passwordEncoder, EmailServiceImpl emailService) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundExceptionClass {
        MyUser myUser = null;
        if(myUser == null){
            throw new NotFoundExceptionClass(ValidationConfig.NOTFOUND_USER);
        }
        return myUser;
    }

    public MemberCreated adding(MemberRequest request) throws MessagingException {
        return authRepository.save(request.toEntity(emailService.verifyCode(request.getEmail()))).created();
    }

//    public Integer getUserId(String equalsUsername) {
//        return null;
//    }

//    @Override
//    public InfoChangePassword changePassword(Integer id, InfoChangePassword password) {
//        String pass = passwordEncoder.encode(password.getCurrentPassword());
//        String newpass = passwordEncoder.encode(password.getNewPassword());
//        if (!passwordEncoder.matches(password.getCurrentPassword(),null)) {
//            throw new IllegalArgumentException("Current Password isn't correct. Please Try Again.");
//        }
//        if (passwordEncoder.matches(password.getNewPassword(),null)){
//            throw new IllegalArgumentException("your new password is still the same with your old password");
//        }
//        if (!password.getNewPassword().equals(password.getConfirmPassword())) {
//            throw new IllegalArgumentException("your confirm password is not match with your new password");
//        }
//        password.setNewPassword(newpass);
//        return null;
//    }
//
//    public String getUserName(String loginUsername) {
//        String username = null;
//        if (username == null) {
//            throw new NotFoundExceptionClass("User Not Found");
//        }
//        return username;
//    }
}
