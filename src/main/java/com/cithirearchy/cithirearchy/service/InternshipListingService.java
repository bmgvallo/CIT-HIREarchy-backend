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

    // CHANGED: Now accepts String course instead of Long courseId
    public List<InternshipListing> getListingsByCourse(String course) {
        return listingRepository.findByCourse(course);
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
    
    public boolean deleteListing(Long id) {
        if (listingRepository.existsById(id)) {
            listingRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // IMPROVED: Now uses repository method instead of filtering in service
    public List<InternshipListing> getListingsByStatus(String status) {
        return listingRepository.findByStatus(status);
    }
    
    // NEW: Method to get listings for a specific student based on their course
    public List<InternshipListing> getListingsForStudent(String studentCourse) {
        return listingRepository.findByCourse(studentCourse);
    }
}