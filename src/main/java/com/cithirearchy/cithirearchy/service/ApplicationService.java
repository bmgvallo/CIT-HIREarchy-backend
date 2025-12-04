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
        
        Application savedApp = applicationRepository.save(application);
        
        return savedApp;
    }

    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    public List<Application> getApplicationsByListing(Long listingId) {
        List<Application> applications = applicationRepository.findByInternshipListingListingID(listingId);        
        return applications;
    }

    public boolean deleteApplication(Long applicationId) {
        Optional<Application> application = applicationRepository.findById(applicationId);
        if (application.isPresent()) {
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