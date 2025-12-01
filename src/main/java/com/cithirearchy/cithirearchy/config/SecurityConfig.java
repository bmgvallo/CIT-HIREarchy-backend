package com.cithirearchy.cithirearchy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable Spring Security's CORS (we're using our own filter)
            .cors(AbstractHttpConfigurer::disable)
            
            // Disable CSRF for API testing
            .csrf(AbstractHttpConfigurer::disable)
            
            // Allow all requests without authentication
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/**").permitAll()
                .anyRequest().permitAll()
            );
        
        return http.build();
    }
}