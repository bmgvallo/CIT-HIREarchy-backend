package com.cithirearchy.cithirearchy.controller;

import com.cithirearchy.cithirearchy.entity.Company;
import com.cithirearchy.cithirearchy.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    public Company registerCompany(@RequestBody Company company) {
        return companyService.registerCompany(company);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCompany(@RequestBody LoginRequest loginRequest) {
        Optional<Company> company = companyService.loginCompany(loginRequest.getEmail(), loginRequest.getPassword());
        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Optional<Company> company = companyService.getCompanyById(id);
        return company.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Company> updateCompanyStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest request) {
        Company updatedCompany = companyService.updateCompanyStatus(id, request.getStatus());
        return updatedCompany != null ? ResponseEntity.ok(updatedCompany) : ResponseEntity.notFound().build();
    }
}

class StatusUpdateRequest {
    private String status;
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getFeedback() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFeedback'");
    }
}