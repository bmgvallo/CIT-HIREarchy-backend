package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "coordinator")
@PrimaryKeyJoinColumn(name = "user_id")
public class Coordinator extends User {
    
    private String coordinatorName;
    private String coordinatorDepartment;

    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL)
    private List<Company> companies;

    // Constructors
    public Coordinator() {
        super();
        setRoleId("25-101");
    }
    
    public Coordinator(String username, String password, String email, String coordinatorName, String coordinatorDepartment) {
        super(username, password, email, "25-101");
        this.coordinatorName = coordinatorName;
        this.coordinatorDepartment = coordinatorDepartment;
    }

    // Getters and Setters
    public String getCoordinatorName() { return coordinatorName; }
    public void setCoordinatorName(String coordinatorName) { this.coordinatorName = coordinatorName; }

    public String getCoordinatorDepartment() { return coordinatorDepartment; }
    public void setCoordinatorDepartment(String coordinatorDepartment) { this.coordinatorDepartment = coordinatorDepartment; }

    public List<Company> getCompanies() { return companies; }
    public void setCompanies(List<Company> companies) { this.companies = companies; }
    
    // Convenience method to get coordinator ID
    public Long getCoordinatorID() { return super.getId(); }
}