package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyID;

    private String companyName;
    private String companyDescription;
    private String companyWebsite;
    private String companyStatus;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;

    @ManyToOne
    @JoinColumn(name = "coordinatorID")
    private Coordinator coordinator;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<InternshipListing> internshipListings;

    public Long getCompanyID() { return companyID; }
    public void setCompanyID(Long companyID) { this.companyID = companyID; }

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

    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public Coordinator getCoordinator() { return coordinator; }
    public void setCoordinator(Coordinator coordinator) { this.coordinator = coordinator; }

    public List<InternshipListing> getInternshipListings() { return internshipListings; }
    public void setInternshipListings(List<InternshipListing> internshipListings) { this.internshipListings = internshipListings; }
}