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
    
    public InternshipListing getListingById(Long id) {
        return listingRepository.findById(id).orElse(null);
    }
    
    public InternshipListing createListing(InternshipListing listing) {
        return listingRepository.save(listing);
    }
    
    public List<InternshipListing> getAllListings() {
        return listingRepository.findAll();
    }
    
    public List<InternshipListing> getListingsByCompany(Long companyId) {
        return listingRepository.findByCompanyId(companyId);
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
    
    // Add this method for CoordinatorController
    public InternshipListing updateListingStatus(Long listingId, String status, String rejectionReason) {
        InternshipListing listing = listingRepository.findById(listingId).orElse(null);
        if (listing != null) {
            listing.setStatus(status);
            if (rejectionReason != null) {
                listing.setRejectionReason(rejectionReason);
            }
            return listingRepository.save(listing);
        }
        return null;
    }
    
    // Add delete method
    public boolean deleteListing(Long id) {
        if (listingRepository.existsById(id)) {
            listingRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Add method to get listings by status
    public List<InternshipListing> getListingsByStatus(String status) {
        // Since we don't have a direct repository method for status yet,
        // we'll filter in service (you can add this to repository later)
        List<InternshipListing> allListings = listingRepository.findAll();
        return allListings.stream()
                .filter(listing -> status.equals(listing.getStatus()))
                .toList();
    }
}