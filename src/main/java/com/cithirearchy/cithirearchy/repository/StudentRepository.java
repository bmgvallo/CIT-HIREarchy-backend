package com.cithirearchy.cithirearchy.repository;

import com.cithirearchy.cithirearchy.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudEmail(String email);
    List<Student> findByStudProgram(String program);
    List<Student> findByCourseCourseID(Long courseId);
}