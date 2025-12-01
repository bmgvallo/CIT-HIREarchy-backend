package com.cithirearchy.cithirearchy.controller;

import com.cithirearchy.cithirearchy.entity.Application;
import com.cithirearchy.cithirearchy.service.ApplicationService;
import com.cithirearchy.cithirearchy.dto.StatusUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
// @CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/{id}")
    public ResponseEntity<Void> handleOptionsForDelete(@PathVariable Long id) {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS")
                .header("Access-Control-Allow-Headers", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }

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

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long applicationId) {
        try {
            boolean deleted = applicationService.deleteApplication(applicationId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                // Application exists but not pending - return 409 Conflict
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of(
                                "message", "Cannot delete application - only pending applications can be withdrawn",
                                "error", "DELETE_NOT_ALLOWED"));
            }
        } catch (IllegalStateException e) {
            // This catches the exception from service when trying to delete non-pending app
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", e.getMessage(),
                            "error", "ILLEGAL_STATE"));
        } catch (Exception e) {
            // Application not found - return 404
            return ResponseEntity.notFound().build();
        }
    }

}