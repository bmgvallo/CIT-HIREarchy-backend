package com.cithirearchy.cithirearchy.service;

import com.cithirearchy.cithirearchy.entity.Student;
import com.cithirearchy.cithirearchy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByStudEmail(email);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudentProfile(Long id, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setStudName(studentDetails.getStudName());
            student.setStudEmail(studentDetails.getStudEmail());
            student.setStudPassword(studentDetails.getStudPassword());
            student.setStudProgram(studentDetails.getStudProgram());
            student.setStudYrLevel(studentDetails.getStudYrLevel());
            student.setResumeURL(studentDetails.getResumeURL());
            student.setStudGPA(studentDetails.getStudGPA());
            return studentRepository.save(student);
        }
        return null;
    }

    public List<Student> getStudentsByProgram(String program) {
        return studentRepository.findByStudProgram(program);
    }

    public Optional<Student> loginStudent(String email, String password) {
        Optional<Student> student = studentRepository.findByStudEmail(email);
        if (student.isPresent() && student.get().getStudPassword().equals(password)) {
            return student;
        }
        return Optional.empty();
    }
}