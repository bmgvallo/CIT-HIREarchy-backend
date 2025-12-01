package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.Coordinator;
import com.cithirearchy.cithirearchy.entity.InternshipListing;
import com.cithirearchy.cithirearchy.repository.CoordinatorRepository;
import com.cithirearchy.cithirearchy.repository.InternshipListingRepository;
import com.cithirearchy.cithirearchy.util.DepartmentCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoordinatorService {
    
    @Autowired
    private CoordinatorRepository coordinatorRepository;
    
    @Autowired
    private InternshipListingRepository listingRepository;  // ADD THIS
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // ===== EXISTING METHODS =====
    public Coordinator registerCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    public Optional<Coordinator> getCoordinatorById(Long id) {
        return coordinatorRepository.findById(id);
    }

    public Optional<Coordinator> getCoordinatorByEmail(String email) {
        return coordinatorRepository.findByEmail(email);
    }

    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    public Coordinator updateCoordinatorProfile(Long id, Coordinator coordinatorDetails) {
        Optional<Coordinator> optionalCoordinator = coordinatorRepository.findById(id);
        if (optionalCoordinator.isPresent()) {
            Coordinator coordinator = optionalCoordinator.get();
            coordinator.setCoordinatorName(coordinatorDetails.getCoordinatorName());
            coordinator.setEmail(coordinatorDetails.getEmail());
            coordinator.setCoordinatorDepartment(coordinatorDetails.getCoordinatorDepartment());
            return coordinatorRepository.save(coordinator);
        }
        return null;
    }
    
    public Coordinator updateCoordinatorPassword(Long id, String newPassword) {
        Optional<Coordinator> optionalCoordinator = coordinatorRepository.findById(id);
        if (optionalCoordinator.isPresent()) {
            Coordinator coordinator = optionalCoordinator.get();
            coordinator.setPassword(passwordEncoder.encode(newPassword));
            return coordinatorRepository.save(coordinator);
        }
        return null;
    }
    
    // ===== NEW METHODS FOR DEPARTMENT-BASED LISTINGS =====
    
    // NEW: Get listings for coordinator's department
    public List<InternshipListing> getListingsForCoordinatorDepartment(String department) {
        // Get all courses in this department
        List<String> departmentCourses = DepartmentCourseMapper.getCoursesForDepartment(department);
        
        // Find listings that have any of these courses
        List<InternshipListing> allListings = listingRepository.findAll();
        
        return allListings.stream()
            .filter(listing -> listing.getCourses() != null)
            .filter(listing -> listing.getCourses().stream()
                .anyMatch(course -> DepartmentCourseMapper.isCourseInDepartment(course, department)))
            .collect(Collectors.toList());
    }
    
    // NEW: Get pending listings for coordinator's department
    public List<InternshipListing> getPendingListingsForDepartment(String department) {
        List<InternshipListing> departmentListings = getListingsForCoordinatorDepartment(department);
        return departmentListings.stream()
            .filter(listing -> "pending".equals(listing.getStatus()))
            .collect(Collectors.toList());
    }
}