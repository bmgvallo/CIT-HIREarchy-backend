package com.cithirearchy.cithirearchy.repository;

import com.cithirearchy.cithirearchy.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentId(Long studentId);
    
    List<Application> findByInternshipListingListingID(Long listingId);
    List<Application> findByStatus(String status);
    
    Optional<Application> findByStudentIdAndInternshipListingListingID(Long studentId, Long listingId);
}