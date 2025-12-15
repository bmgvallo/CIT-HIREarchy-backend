package com.cithirearchy.cithirearchy.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    
    private String studName;
    private String studYrLevel;
    // private String resumeURL; 
    
    @Column(name = "stud_gpa")
    private Double studGPA;

    private String course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Application> applications;

    public Student() {
        super();
        setRoleId("25-103");
    }
    
    public Student(String username, String password, String email, String studName, 
                String studYrLevel, String course, Double studGPA) {
        super(username, password, email, "25-103");
        this.studName = studName;
        this.studYrLevel = studYrLevel;
        this.course = course;
        this.studGPA = studGPA;
    }

    public String getStudName() { return studName; }
    public void setStudName(String studName) { this.studName = studName; }

    public String getStudYrLevel() { return studYrLevel; }
    public void setStudYrLevel(String studYrLevel) { this.studYrLevel = studYrLevel; }

    // public String getResumeURL() { return resumeURL; }
    // public void setResumeURL(String resumeURL) { this.resumeURL = resumeURL; }

    public Double getStudGPA() { return studGPA; }
    public void setStudGPA(Double studGPA) { this.studGPA = studGPA; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
    
    public Long getStudID() { return super.getId(); }
}