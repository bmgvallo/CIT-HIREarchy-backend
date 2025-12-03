package com.cithirearchy.cithirearchy.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "company")
@PrimaryKeyJoinColumn(name = "user_id")
public class Company extends User {
    
    private String companyName;
    private String companyDescription;
    private String companyWebsite;
    private String contactPerson;
    private String contactPhone;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonIgnore // Add this to prevent circular serialization
    private List<InternshipListing> internshipListings;

    public Company() {
        super();
        setRoleId("25-102");
    }
    
    public Company(String username, String password, String email, String companyName, 
                   String companyDescription, String contactPerson, String contactPhone) {
        super(username, password, email, "25-102");
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
    }
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyDescription() { return companyDescription; }
    public void setCompanyDescription(String companyDescription) { this.companyDescription = companyDescription; }

    public String getCompanyWebsite() { return companyWebsite; }
    public void setCompanyWebsite(String companyWebsite) { this.companyWebsite = companyWebsite; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public List<InternshipListing> getInternshipListings() { return internshipListings; }
    public void setInternshipListings(List<InternshipListing> internshipListings) { this.internshipListings = internshipListings; }
    
    public Long getCompanyID() { return super.getId(); }
}