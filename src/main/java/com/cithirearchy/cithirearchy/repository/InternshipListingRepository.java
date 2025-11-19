package com.cithirearchy.cithirearchy.repository;

import com.cithirearchy.cithirearchy.entity.InternshipListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InternshipListingRepository extends JpaRepository<InternshipListing, Long> {
    List<InternshipListing> findByCompanyCompanyID(Long companyId);
    List<InternshipListing> findByCourseCourseID(Long courseId);
    List<InternshipListing> findByLocationContaining(String location);
    List<InternshipListing> findByModality(String modality);
    List<InternshipListing> findByTitleContaining(String title);
}
