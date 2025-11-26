package com.cithirearchy.cithirearchy.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class InternshipListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingID;

    private String title;
    private String description;
    private String location;
    private String modality;
    private String requirements;
    private LocalDate postDate;
    private String duration;
    private LocalDate deadline;
    private Double salary;
    private String status = "pending"; //default status
    private String rejectionReason;

    @ManyToOne
    @JoinColumn(name = "companyID")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "courseID")
    private Course course;

    @OneToMany(mappedBy = "internshipListing", cascade = CascadeType.ALL)
    private List<Application> applications;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }

    public Long getListingID() { return listingID; }
    public void setListingID(Long listingID) { this.listingID = listingID; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getModality() { return modality; }
    public void setModality(String modality) { this.modality = modality; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }

    public LocalDate getPostDate() { return postDate; }
    public void setPostDate(LocalDate postDate) { this.postDate = postDate; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
}
