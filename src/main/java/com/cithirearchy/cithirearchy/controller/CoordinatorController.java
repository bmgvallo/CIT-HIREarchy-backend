package com.cithirearchy.cithirearchy.controller;
import com.cithirearchy.cithirearchy.service.CoordinatorService;


import com.cithirearchy.cithirearchy.entity.Coordinator;
import com.cithirearchy.cithirearchy.entity.InternshipListing;
import com.cithirearchy.cithirearchy.service.InternshipListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/coordinator")
@CrossOrigin(origins = "http://localhost:3000")
public class CoordinatorController {
    
    @Autowired
    private InternshipListingService listingService;

    @Autowired
    private CoordinatorService coordinatorService; 
    
    @GetMapping("/jobs")
    public List<InternshipListing> getAllJobsForReview() {
        return listingService.getAllListings();
    }
    
    @GetMapping("/jobs/status/{status}")
    public List<InternshipListing> getJobsByStatus(@PathVariable String status) {
        return listingService.getListingsByStatus(status);
    }
    
    @PostMapping("/jobs/{jobId}/approve")
    public ResponseEntity<InternshipListing> approveJob(@PathVariable Long jobId) {
        InternshipListing updatedListing = listingService.updateListingStatus(jobId, "approved", null);
        if (updatedListing != null) {
            return ResponseEntity.ok(updatedListing);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/jobs/{jobId}/reject")
    public ResponseEntity<InternshipListing> rejectJob(@PathVariable Long jobId, @RequestBody Map<String, String> request) {
        String reason = request.get("reason");
        InternshipListing updatedListing = listingService.updateListingStatus(jobId, "rejected", reason);
        if (updatedListing != null) {
            return ResponseEntity.ok(updatedListing);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Coordinator> getCoordinatorProfile(@PathVariable Long id) {
        Optional<Coordinator> coordinator = coordinatorService.getCoordinatorById(id);
        return coordinator.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/debug/{id}")
    public ResponseEntity<Map<String, Object>> debugCoordinator(@PathVariable Long id) {
        Optional<Coordinator> coordinator = coordinatorService.getCoordinatorById(id);
        if (coordinator.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", coordinator.get().getId());
            response.put("username", coordinator.get().getUsername());
            response.put("email", coordinator.get().getEmail());
            response.put("coordinatorName", coordinator.get().getCoordinatorName());
            response.put("coordinatorDepartment", coordinator.get().getCoordinatorDepartment());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}