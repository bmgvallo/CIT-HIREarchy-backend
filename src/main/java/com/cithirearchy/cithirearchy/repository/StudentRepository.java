package com.cithirearchy.cithirearchy.repository;

import com.cithirearchy.cithirearchy.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);
    Optional<Student> findByEmail(String email);
    
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    
    List<Student> findByCourse(String course);
}