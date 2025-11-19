package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.InternshipListing;
import com.cithirearchy.cithirearchy.repository.InternshipListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InternshipListingService {
    
    @Autowired
    private InternshipListingRepository listingRepository;

    public InternshipListing createListing(InternshipListing listing) {
        return listingRepository.save(listing);
    }

    public List<InternshipListing> getAllListings() {
        return listingRepository.findAll();
    }

    public List<InternshipListing> getListingsByCompany(Long companyId) {
        return listingRepository.findByCompanyCompanyID(companyId);
    }

    public List<InternshipListing> getListingsByCourse(Long courseId) {
        return listingRepository.findByCourseCourseID(courseId);
    }

    public List<InternshipListing> searchListingsByLocation(String location) {
        return listingRepository.findByLocationContaining(location);
    }

    public List<InternshipListing> searchListingsByTitle(String title) {
        return listingRepository.findByTitleContaining(title);
    }

    public List<InternshipListing> getListingsByModality(String modality) {
        return listingRepository.findByModality(modality);
    }
}