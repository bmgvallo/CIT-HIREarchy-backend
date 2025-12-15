package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.Student;
import com.cithirearchy.cithirearchy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudentProfile(Long id, Student studentDetails) {
    Optional<Student> optionalStudent = studentRepository.findById(id);
    if (optionalStudent.isPresent()) {
        Student student = optionalStudent.get();
        student.setStudName(studentDetails.getStudName());
        student.setEmail(studentDetails.getEmail());
        student.setStudYrLevel(studentDetails.getStudYrLevel());
        // student.setResumeURL(studentDetails.getResumeURL());
        student.setStudGPA(studentDetails.getStudGPA());
        student.setCourse(studentDetails.getCourse());
        return studentRepository.save(student);
    }
    return null;
}
    
    public Student updateStudentPassword(Long id, String newPassword) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setPassword(passwordEncoder.encode(newPassword));
            return studentRepository.save(student);
        }
        return null;
    }

    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    public Optional<Student> loginStudent(String email, String password) {
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent() && passwordEncoder.matches(password, student.get().getPassword())) {
            return student;
        }
        return Optional.empty();
    }
}