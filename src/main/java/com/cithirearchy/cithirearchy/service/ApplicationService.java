package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.Application;
import com.cithirearchy.cithirearchy.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application submitApplication(Application application) {
        application.setApplyDate(LocalDate.now());
        application.setStatus("PENDING");
        
        // Debug logging
        System.out.println("=== SUBMITTING APPLICATION ===");
        System.out.println("Resume URL: " + application.getResumeURL());
        System.out.println("Cover Letter (first 50 chars): " + 
            (application.getCoverLetter() != null ? 
             application.getCoverLetter().substring(0, Math.min(50, application.getCoverLetter().length())) : "NULL"));
        
        Application savedApp = applicationRepository.save(application);
        
        System.out.println("Application saved with ID: " + savedApp.getApplicationID());
        System.out.println("Saved Resume URL: " + savedApp.getResumeURL());
        System.out.println("========================");
        
        return savedApp;
    }

    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    public List<Application> getApplicationsByListing(Long listingId) {
        List<Application> applications = applicationRepository.findByInternshipListingListingID(listingId);
        
        // Debug logging
        System.out.println("=== FETCHING APPLICATIONS FOR LISTING " + listingId + " ===");
        for (Application app : applications) {
            System.out.println("App ID: " + app.getApplicationID());
            System.out.println("  - Resume URL: " + app.getResumeURL());
            System.out.println("  - Cover Letter present: " + (app.getCoverLetter() != null));
            System.out.println("  - Status: " + app.getStatus());
        }
        System.out.println("Total applications: " + applications.size());
        System.out.println("========================");
        
        return applications;
    }

    public boolean deleteApplication(Long applicationId) {
        Optional<Application> application = applicationRepository.findById(applicationId);
        if (application.isPresent()) {
            // Only allow deletion if status is PENDING
            if ("PENDING".equalsIgnoreCase(application.get().getStatus())) {
                applicationRepository.deleteById(applicationId);
                return true;
            } else {
                throw new IllegalStateException(
                        "Cannot delete application with status: " + application.get().getStatus());
            }
        }
        return false;
    }

    public Application updateApplicationStatus(Long applicationId, String status, String feedback) {
        Application application = applicationRepository.findById(applicationId).orElse(null);
        if (application != null) {
            application.setStatus(status);
            application.setFeedback(feedback);
            return applicationRepository.save(application);
        }
        return null;
    }

    public List<Application> countApplicationsByStatus(String status) {
        return applicationRepository.findByStatus(status);
    }
}