package com.cithirearchy.cithirearchy.repository;

import com.cithirearchy.cithirearchy.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByContactEmail(String email);
    List<Company> findByCompanyStatus(String status);
    List<Company> findByCoordinatorCoordinatorID(Long coordinatorId);
}