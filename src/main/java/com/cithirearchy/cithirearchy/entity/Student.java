package com.cithirearchy.cithirearchy.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studID;

    private String studName;
    private String studEmail;
    private String studPassword;
    private String studProgram;
    private String studYrLevel;
    private String resumeURL; 
    private Double studGPA;

    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Application> applications;

    public Long getStudID() { return studID; }
    public void setStudID(Long studID) { this.studID = studID; }

    public String getStudName() { return studName; }
    public void setStudName(String studName) { this.studName = studName; }

    public String getStudEmail() { return studEmail; }
    public void setStudEmail(String studEmail) { this.studEmail = studEmail; }

    public String getStudPassword() { return studPassword; }
    public void setStudPassword(String studPassword) { this.studPassword = studPassword; }

    public String getStudProgram() { return studProgram; }
    public void setStudProgram(String studProgram) { this.studProgram = studProgram; }

    public String getStudYrLevel() { return studYrLevel; }
    public void setStudYrLevel(String studYrLevel) { this.studYrLevel = studYrLevel; }

    public String getResumeURL() { return resumeURL; }
    public void setResumeURL(String resumeURL) { this.resumeURL = resumeURL; }

    public Double getStudGPA() { return studGPA; }
    public void setStudGPA(Double studGPA) { this.studGPA = studGPA; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
}
