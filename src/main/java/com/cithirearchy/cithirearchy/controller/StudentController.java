package com.cithirearchy.cithirearchy.controller;

import com.cithirearchy.cithirearchy.entity.Student;
import com.cithirearchy.cithirearchy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        try {
            // Check if email already exists using new method
            Optional<Student> existingStudent = studentService.getStudentByEmail(student.getEmail());
            if (existingStudent.isPresent()) {
                return ResponseEntity.badRequest().body("Email already exists");
            }
            
            Student savedStudent = studentService.registerStudent(student);
            return ResponseEntity.ok(savedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering student");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<Student> student = studentService.loginStudent(loginRequest.getEmail(), loginRequest.getPassword());
            if (student.isPresent()) {
                return ResponseEntity.ok(student.get());
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        try {
            Optional<Student> student = studentService.getStudentById(id);
            return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentService.updateStudentProfile(id, studentDetails);
            return updatedStudent != null ? ResponseEntity.ok(updatedStudent) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        try {
            Optional<Student> student = studentService.getStudentByEmail(email);
            return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/debug/{id}")
    public ResponseEntity<Map<String, Object>> debugStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", student.get().getId());
            response.put("username", student.get().getUsername());
            response.put("email", student.get().getEmail());
            response.put("studName", student.get().getStudName());
            response.put("studProgram", student.get().getStudProgram());
            response.put("studYrLevel", student.get().getStudYrLevel());
            response.put("studGPA", student.get().getStudGPA());
            response.put("resumeURL", student.get().getResumeURL());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}

class LoginRequest {
    private String email;
    private String password;
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}