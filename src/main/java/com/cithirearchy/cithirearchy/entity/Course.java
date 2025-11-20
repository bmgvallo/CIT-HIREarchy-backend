package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseID;

    private String courseName;
    private String courseDepartment;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<InternshipListing> internshipListings;

    public Long getCourseID() { return courseID; }
    public void setCourseID(Long courseID) { this.courseID = courseID; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseDepartment() { return courseDepartment; }
    public void setCourseDepartment(String courseDepartment) { this.courseDepartment = courseDepartment; }

    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }

    public List<InternshipListing> getInternshipListings() { return internshipListings; }
    public void setInternshipListings(List<InternshipListing> internshipListings) { this.internshipListings = internshipListings; }
}