package com.cithirearchy.cithirearchy.controller;

import com.cithirearchy.cithirearchy.entity.Application;
import com.cithirearchy.cithirearchy.service.ApplicationService;
import com.cithirearchy.cithirearchy.dto.StatusUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {
    
    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public Application submitApplication(@RequestBody Application application) {
        return applicationService.submitApplication(application);
    }

    @GetMapping("/student/{studentId}")
    public List<Application> getApplicationsByStudent(@PathVariable Long studentId) {
        return applicationService.getApplicationsByStudent(studentId);
    }

    @GetMapping("/listing/{listingId}")
    public List<Application> getApplicationsByListing(@PathVariable Long listingId) {
        return applicationService.getApplicationsByListing(listingId);
    }

    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable Long applicationId, 
            @RequestBody StatusUpdateRequest request) {
        Application updatedApplication = applicationService.updateApplicationStatus(
            applicationId, request.getStatus(), request.getFeedback());
        return updatedApplication != null ? ResponseEntity.ok(updatedApplication) : ResponseEntity.notFound().build();
    }

    @GetMapping("/stats/status/{status}")
    public List<Application> countApplicationsByStatus(@PathVariable String status) {
        return applicationService.countApplicationsByStatus(status);
    }
}