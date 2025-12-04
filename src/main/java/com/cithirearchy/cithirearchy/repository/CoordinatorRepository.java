package com.cithirearchy.cithirearchy.repository;

import com.cithirearchy.cithirearchy.entity.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
    Optional<Coordinator> findByUsername(String username);
    Optional<Coordinator> findByEmail(String email);
    
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}