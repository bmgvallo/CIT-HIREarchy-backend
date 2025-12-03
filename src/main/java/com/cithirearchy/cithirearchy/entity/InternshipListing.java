package com.cithirearchy.cithirearchy.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "internship_listing")
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
    private String status = "pending"; // default status
    private String rejectionReason;

    // CHANGED: Replace Course reference with String array for courses
    @ElementCollection
    @CollectionTable(name = "listing_courses", joinColumns = @JoinColumn(name = "listing_id"))
    @Column(name = "course")
    private List<String> courses; // Stores multiple courses as String array

    @ManyToOne(fetch = FetchType.EAGER) // Add FetchType.EAGER
    @JoinColumn(name = "companyID")
    @JsonIgnoreProperties({ "internshipListings", "password" }) // Prevent circular reference
    private Company company;

    @OneToMany(mappedBy = "internshipListing", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Application> applications;

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Long getListingID() {
        return listingID;
    }

    public void setListingID(Long listingID) {
        this.listingID = listingID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // CHANGED: Getter/Setter for courses (List<String> instead of Course entity)
    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}