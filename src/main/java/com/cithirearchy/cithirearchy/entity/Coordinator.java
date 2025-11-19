package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coordinatorID;

    private String coordinatorName;
    private String coordinatorEmail;
    private String coordinatorPassword;
    private String coordinatorDepartment;

    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL)
    private List<Company> companies;

    public Long getCoordinatorID() { return coordinatorID; }
    public void setCoordinatorID(Long coordinatorID) { this.coordinatorID = coordinatorID; }

    public String getCoordinatorName() { return coordinatorName; }
    public void setCoordinatorName(String coordinatorName) { this.coordinatorName = coordinatorName; }

    public String getCoordinatorEmail() { return coordinatorEmail; }
    public void setCoordinatorEmail(String coordinatorEmail) { this.coordinatorEmail = coordinatorEmail; }

    public String getCoordinatorPassword() { return coordinatorPassword; }
    public void setCoordinatorPassword(String coordinatorPassword) { this.coordinatorPassword = coordinatorPassword; }

    public String getCoordinatorDepartment() { return coordinatorDepartment; }
    public void setCoordinatorDepartment(String coordinatorDepartment) { this.coordinatorDepartment = coordinatorDepartment; }

    public List<Company> getCompanies() { return companies; }
    public void setCompanies(List<Company> companies) { this.companies = companies; }
}
