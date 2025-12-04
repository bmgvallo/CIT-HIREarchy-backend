package com.cithirearchy.cithirearchy.repository;

import com.cithirearchy.cithirearchy.entity.InternshipListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InternshipListingRepository extends JpaRepository<InternshipListing, Long> {
    List<InternshipListing> findByCompanyId(Long companyId);
    
    @Query("SELECT il FROM InternshipListing il JOIN il.courses c WHERE c = :course")
    List<InternshipListing> findByCourse(@Param("course") String course);

    @Query("SELECT il FROM InternshipListing il JOIN il.courses c WHERE c = :course AND il.status = 'approved'")
    List<InternshipListing> findApprovedListingsByCourse(@Param("course") String course);
    
    List<InternshipListing> findByLocationContaining(String location);
    List<InternshipListing> findByModality(String modality);
    List<InternshipListing> findByTitleContaining(String title);
    
    List<InternshipListing> findByStatus(String status);
}