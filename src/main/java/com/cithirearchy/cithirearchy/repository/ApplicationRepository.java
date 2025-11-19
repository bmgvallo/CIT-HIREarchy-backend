package com.cithirearchy.cithirearchy.repository;

import com.cithirearchy.cithirearchy.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentStudID(Long studentId);
    List<Application> findByInternshipListingListingID(Long listingId);
    List<Application> findByStatus(String status);
    Long countByStatus(String status);
}