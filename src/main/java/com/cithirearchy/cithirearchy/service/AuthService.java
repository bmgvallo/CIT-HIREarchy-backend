// src/main/java/com/cithirearchy/cithirearchy/service/AuthService.java
package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.*;
import com.cithirearchy.cithirearchy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    
    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private CoordinatorRepository coordinatorRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Unified login across all user types
    public Map<String, Object> login(String username, String password) {
        // Try Company first
        Optional<Company> company = companyRepository.findByUsername(username);
        if (company.isPresent() && passwordEncoder.matches(password, company.get().getPassword())) {
            return createLoginResponse(company.get(), "company");
        }
        
        // Try Coordinator
        Optional<Coordinator> coordinator = coordinatorRepository.findByUsername(username);
        if (coordinator.isPresent() && passwordEncoder.matches(password, coordinator.get().getPassword())) {
            return createLoginResponse(coordinator.get(), "coordinator");
        }
        
        // Try Student
        Optional<Student> student = studentRepository.findByUsername(username);
        if (student.isPresent() && passwordEncoder.matches(password, student.get().getPassword())) {
            return createLoginResponse(student.get(), "student");
        }
        
        return null; // Login failed
    }
    
    private Map<String, Object> createLoginResponse(User user, String userType) {
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("role", user.getRoleId());
        response.put("userType", userType);
        return response;
    }
    
    // Registration methods
    public Coordinator registerCoordinator(Coordinator coordinator, String rawPassword) {
        if (coordinatorRepository.existsByUsername(coordinator.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (coordinatorRepository.existsByEmail(coordinator.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        coordinator.setPassword(passwordEncoder.encode(rawPassword));
        coordinator.setRoleId("25-101"); // Coordinator role
        return coordinatorRepository.save(coordinator);
    }
    
    public Company registerCompany(Company company, String rawPassword) {
        if (companyRepository.existsByUsername(company.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        company.setPassword(passwordEncoder.encode(rawPassword));
        company.setRoleId("25-102"); // Company role
        company.setCompanyStatus("Pending"); // Default status
        return companyRepository.save(company);
    }
    
    public Student registerStudent(Student student, String rawPassword) {
        if (studentRepository.existsByUsername(student.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        student.setPassword(passwordEncoder.encode(rawPassword));
        student.setRoleId("25-103"); // Student role
        return studentRepository.save(student);
    }
    
    // Utility methods for checking duplicates
    public boolean usernameExists(String username) {
        return companyRepository.existsByUsername(username) ||
               coordinatorRepository.existsByUsername(username) ||
               studentRepository.existsByUsername(username);
    }
    
    public boolean emailExists(String email) {
        return companyRepository.existsByEmail(email) ||
               coordinatorRepository.existsByEmail(email) ||
               studentRepository.existsByEmail(email);
    }
}