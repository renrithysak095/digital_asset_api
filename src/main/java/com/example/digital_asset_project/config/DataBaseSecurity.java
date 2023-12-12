package com.example.digital_asset_project.config;

import com.example.digital_asset_project.feature.authentication.service.AuthServiceImpl;
import com.example.digital_asset_project.jwt.JwtAuthEntryPoint;
import com.example.digital_asset_project.jwt.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Configuration
public class DataBaseSecurity{

    private final AuthServiceImpl authServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenFilter jwtTokenFilter;
    private final JwtAuthEntryPoint authEntryPoint;

    public DataBaseSecurity(AuthServiceImpl authServiceImpl, PasswordEncoder passwordEncoder, JwtTokenFilter jwtTokenFilter, JwtAuthEntryPoint authEntryPoint) {
        this.authServiceImpl = authServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenFilter = jwtTokenFilter;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authServiceImpl);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf()
                .disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/api/v1/auth/change-password",
                                "/api/v1/auth/login",
                                "/api/v1/auth",
                                "/v3/api-docs/**",
                                "/api/v1/images/Files",
                                "/api/v1/images/**",
                                "/swagger-ui/**",
                                "/swagger/ui.html").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.setAllowedHeaders(
                Arrays.asList(
                        "X-Requested-With",
                        "Origin",
                        "Content-Type",
                        "Accept",
                        "Authorization",
                        "Access-Control-Allow-Credentials",
                        "Access-Control-Allow-Headers",
                        "Access-Control-Allow-Methods",
                        "Access-Control-Allow-Origin",
                        "Access-Control-Expose-Headers",
                        "Access-Control-Max-Age",
                        "Access-Control-Request-Headers",
                        "Access-Control-Request-Method",
                        "Age",
                        "Allow",
                        "Alternates",
                        "Content-Range",
                        "Content-Disposition",
                        "Content-Description"
                )
        );
        config.setAllowedMethods(
                Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
        );
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
