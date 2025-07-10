package com.scm.scm20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.scm20.service.implement.SecurityCustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService userDetailsService;

   @Bean
public SecurityFilterChain scurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .authorizeHttpRequests(auth -> {
                auth
                        .requestMatchers("/users/**").authenticated();
                auth.anyRequest().permitAll();
            })
            .formLogin();  // ✅ Added this line to enable login form
    return httpSecurity.build();
}
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // ✅ Use the autowired bean
        provider.setPasswordEncoder(passwordEncoder());
        
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}