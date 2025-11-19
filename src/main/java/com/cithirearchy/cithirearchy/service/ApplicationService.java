package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.Application;
import com.cithirearchy.cithirearchy.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {
    
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application submitApplication(Application application) {
        application.setApplyDate(LocalDate.now());
        application.setStatus("PENDING");
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentStudID(studentId);
    }

    public List<Application> getApplicationsByListing(Long listingId) {
        return applicationRepository.findByInternshipListingListingID(listingId);
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

    public Long countApplicationsByStatus(String status) {
        return applicationRepository.countByStatus(status);
    }
}