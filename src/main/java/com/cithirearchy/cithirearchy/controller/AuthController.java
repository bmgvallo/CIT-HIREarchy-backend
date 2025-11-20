// src/main/java/com/cithirearchy/cithirearchy/controller/AuthController.java
package com.cithirearchy.cithirearchy.controller;

import com.cithirearchy.cithirearchy.entity.*;
import com.cithirearchy.cithirearchy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Map<String, Object> result = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
            
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }
    
    @PostMapping("/register/coordinator")
    public ResponseEntity<?> registerCoordinator(@RequestBody Coordinator coordinator, 
                                               @RequestParam String password) {
        try {
            Coordinator savedCoordinator = authService.registerCoordinator(coordinator, password);
            return ResponseEntity.ok(savedCoordinator);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
    
    @PostMapping("/register/company")
    public ResponseEntity<?> registerCompany(@RequestBody Company company, 
                                           @RequestParam String password) {
        try {
            Company savedCompany = authService.registerCompany(company, password);
            return ResponseEntity.ok(savedCompany);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
    
    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody Student student, 
                                           @RequestParam String password) {
        try {
            Student savedStudent = authService.registerStudent(student, password);
            return ResponseEntity.ok(savedStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
    
    @GetMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        boolean exists = authService.usernameExists(username);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
    
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = authService.emailExists(email);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
    
    // Login request DTO
    public static class LoginRequest {
        private String username;
        private String password;
        
        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}