package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    
    private String studName;
    private String studProgram;
    private String studYrLevel;
    private String resumeURL; 
    private Double studGPA;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Application> applications;

    // Constructors
    public Student() {
        super();
        setRoleId("25-103");
    }
    
    public Student(String username, String password, String email, String studName, 
                   String studProgram, String studYrLevel) {
        super(username, password, email, "25-103");
        this.studName = studName;
        this.studProgram = studProgram;
        this.studYrLevel = studYrLevel;
    }

    public String getStudName() { return studName; }
    public void setStudName(String studName) { this.studName = studName; }

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
    
    public Long getStudID() { return super.getId(); }
}