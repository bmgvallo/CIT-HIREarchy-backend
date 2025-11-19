package com.cithirearchy.cithirearchy.controller;

import com.cithirearchy.cithirearchy.entity.InternshipListing;
import com.cithirearchy.cithirearchy.service.InternshipListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipListingController {
    
    @Autowired
    private InternshipListingService listingService;

    @PostMapping
    public InternshipListing createListing(@RequestBody InternshipListing listing) {
        return listingService.createListing(listing);
    }

    @GetMapping
    public List<InternshipListing> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping("/company/{companyId}")
    public List<InternshipListing> getListingsByCompany(@PathVariable Long companyId) {
        return listingService.getListingsByCompany(companyId);
    }

    @GetMapping("/course/{courseId}")
    public List<InternshipListing> getListingsByCourse(@PathVariable Long courseId) {
        return listingService.getListingsByCourse(courseId);
    }

    @GetMapping("/search/location")
    public List<InternshipListing> searchByLocation(@RequestParam String location) {
        return listingService.searchListingsByLocation(location);
    }

    @GetMapping("/search/title")
    public List<InternshipListing> searchByTitle(@RequestParam String title) {
        return listingService.searchListingsByTitle(title);
    }

    @GetMapping("/modality/{modality}")
    public List<InternshipListing> getListingsByModality(@PathVariable String modality) {
        return listingService.getListingsByModality(modality);
    }
}