package com.cithirearchy.cithirearchy.controller;

import com.cithirearchy.cithirearchy.entity.InternshipListing;
import com.cithirearchy.cithirearchy.service.InternshipListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipListingController {
    
    @Autowired
    private InternshipListingService listingService;
    
    @GetMapping
    public List<InternshipListing> getAllListings() {
        return listingService.getAllListings();
    }
    
    @GetMapping("/company/{companyId}")
    public List<InternshipListing> getListingsByCompany(@PathVariable Long companyId) {
        return listingService.getListingsByCompany(companyId);
    }
    
    @PostMapping
    public InternshipListing createListing(@RequestBody InternshipListing listing) {
        return listingService.createListing(listing);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InternshipListing> updateListing(@PathVariable Long id, @RequestBody InternshipListing listing) {
        InternshipListing existing = listingService.getListingById(id);
        if (existing != null) {
            listing.setListingID(id);
            return ResponseEntity.ok(listingService.createListing(listing));
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable Long id) {
        boolean deleted = listingService.deleteListing(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}