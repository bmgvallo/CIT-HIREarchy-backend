package com.cithirearchy.cithirearchy.controller;
import com.cithirearchy.cithirearchy.service.CoordinatorService;


import com.cithirearchy.cithirearchy.entity.Coordinator;
import com.cithirearchy.cithirearchy.entity.InternshipListing;
import com.cithirearchy.cithirearchy.service.InternshipListingService;
import com.cithirearchy.cithirearchy.util.DepartmentCourseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/department/jobs")
public ResponseEntity<List<InternshipListing>> getListingsForCoordinatorDepartment(
        @RequestParam Long coordinatorId) {
    Optional<Coordinator> coordinator = coordinatorService.getCoordinatorById(coordinatorId);
    if (coordinator.isPresent()) {
        String department = coordinator.get().getCoordinatorDepartment();
        List<InternshipListing> listings = coordinatorService.getListingsForCoordinatorDepartment(department);
        
        for (InternshipListing listing : listings) {
            if (listing.getCompany() != null && listing.getCompany().getId() != null) {
            }
        }
        
        return ResponseEntity.ok(listings);
    }
    return ResponseEntity.notFound().build();
}
    
    @GetMapping("/department/jobs/pending")
    public ResponseEntity<List<InternshipListing>> getPendingListingsForDepartment(
            @RequestParam Long coordinatorId) {
        Optional<Coordinator> coordinator = coordinatorService.getCoordinatorById(coordinatorId);
        if (coordinator.isPresent()) {
            String department = coordinator.get().getCoordinatorDepartment();
            List<InternshipListing> listings = coordinatorService.getPendingListingsForDepartment(department);
            return ResponseEntity.ok(listings);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/jobs")
    public List<InternshipListing> getAllJobsForReview() {
        return listingService.getAllListings();
    }
    
    @GetMapping("/jobs/status/{status}")
    public List<InternshipListing> getJobsByStatus(@PathVariable String status) {
        return listingService.getListingsByStatus(status);
    }
    
    @PostMapping("/jobs/{jobId}/approve")
    public ResponseEntity<InternshipListing> approveJob(
            @PathVariable Long jobId,
            @RequestParam Long coordinatorId) {
        
        Optional<Coordinator> coordinator = coordinatorService.getCoordinatorById(coordinatorId);
        if (!coordinator.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        
        InternshipListing listing = listingService.getListingById(jobId);
        if (listing == null) {
            return ResponseEntity.notFound().build();
        }
        
        String department = coordinator.get().getCoordinatorDepartment();
        boolean hasPermission = listing.getCourses() != null && 
            listing.getCourses().stream()
                .anyMatch(course -> DepartmentCourseMapper.isCourseInDepartment(course, department));
        
        if (!hasPermission) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(null); // coordinator cannot approve listings outside their department
        }
        
        InternshipListing updatedListing = listingService.updateListingStatus(jobId, "approved", null);
        return updatedListing != null ? ResponseEntity.ok(updatedListing) : ResponseEntity.notFound().build();
    }
    
    // reject job - check if coordinator has permission
    @PostMapping("/jobs/{jobId}/reject")
    public ResponseEntity<InternshipListing> rejectJob(
            @PathVariable Long jobId, 
            @RequestBody Map<String, String> request,
            @RequestParam Long coordinatorId) {
        
        Optional<Coordinator> coordinator = coordinatorService.getCoordinatorById(coordinatorId);
        if (!coordinator.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        
        InternshipListing listing = listingService.getListingById(jobId);
        if (listing == null) {
            return ResponseEntity.notFound().build();
        }
        
        // check if coordinator has permission
        String department = coordinator.get().getCoordinatorDepartment();
        boolean hasPermission = listing.getCourses() != null && 
            listing.getCourses().stream()
                .anyMatch(course -> DepartmentCourseMapper.isCourseInDepartment(course, department));
        
        if (!hasPermission) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(null);
        }
        
        String reason = request.get("reason");
        InternshipListing updatedListing = listingService.updateListingStatus(jobId, "rejected", reason);
        return updatedListing != null ? ResponseEntity.ok(updatedListing) : ResponseEntity.notFound().build();
    }
}