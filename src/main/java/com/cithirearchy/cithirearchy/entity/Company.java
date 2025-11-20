package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@PrimaryKeyJoinColumn(name = "user_id")
public class Company extends User {
    
    private String companyName;
    private String companyDescription;
    private String companyWebsite;
    private String companyStatus;
    private String contactPerson;
    private String contactPhone;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private Coordinator coordinator;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<InternshipListing> internshipListings;

    // Constructors
    public Company() {
        super();
        setRoleId("25-102");
        this.companyStatus = "Pending";
    }
    
    public Company(String username, String password, String email, String companyName, 
                   String companyDescription, String contactPerson, String contactPhone) {
        super(username, password, email, "25-102");
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
        this.companyStatus = "Pending";
    }

    // Getters and Setters
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyDescription() { return companyDescription; }
    public void setCompanyDescription(String companyDescription) { this.companyDescription = companyDescription; }

    public String getCompanyWebsite() { return companyWebsite; }
    public void setCompanyWebsite(String companyWebsite) { this.companyWebsite = companyWebsite; }

    public String getCompanyStatus() { return companyStatus; }
    public void setCompanyStatus(String companyStatus) { this.companyStatus = companyStatus; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public Coordinator getCoordinator() { return coordinator; }
    public void setCoordinator(Coordinator coordinator) { this.coordinator = coordinator; }

    public List<InternshipListing> getInternshipListings() { return internshipListings; }
    public void setInternshipListings(List<InternshipListing> internshipListings) { this.internshipListings = internshipListings; }
    
    // Convenience method to get company ID
    public Long getCompanyID() { return super.getId(); }
}