package com.cithirearchy.cithirearchy.controller;

import com.cithirearchy.cithirearchy.entity.Application;
import com.cithirearchy.cithirearchy.entity.Company;
import com.cithirearchy.cithirearchy.entity.InternshipListing;
import com.cithirearchy.cithirearchy.service.ApplicationService;
import com.cithirearchy.cithirearchy.service.CompanyService;
import com.cithirearchy.cithirearchy.dto.StatusUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @Autowired
    private ApplicationService applicationService;

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

    @GetMapping("/{companyId}/applications")
    public ResponseEntity<List<Application>> getApplicationsForCompany(@PathVariable Long companyId) {
        try {
            Optional<Company> company = companyService.getCompanyById(companyId);
            if (company.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            List<InternshipListing> companyListings = company.get().getInternshipListings();
            if (companyListings == null || companyListings.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            }
            
            List<Application> allApplications = new ArrayList<>();
            for (InternshipListing listing : companyListings) {
                List<Application> listingApplications = applicationService.getApplicationsByListing(listing.getListingID());
                allApplications.addAll(listingApplications);
            }
            
            return ResponseEntity.ok(allApplications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PatchMapping("/applications/{applicationId}/review")
    public ResponseEntity<Application> reviewApplication(
            @PathVariable Long applicationId,
            @RequestBody ApplicationReviewRequest request) {
        
        Application updatedApplication = applicationService.updateApplicationStatus(
            applicationId, request.getStatus(), request.getFeedback());
        
        return updatedApplication != null ? 
            ResponseEntity.ok(updatedApplication) : 
            ResponseEntity.notFound().build();
    }

}

class LoginRequest {
    private String email;
    private String password;
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class ApplicationReviewRequest {
    private String status;
    private String feedback;
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}