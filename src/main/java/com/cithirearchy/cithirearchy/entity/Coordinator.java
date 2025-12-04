package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "coordinator")
@PrimaryKeyJoinColumn(name = "user_id")
public class Coordinator extends User {
    
    private String coordinatorName;
    private String coordinatorDepartment;

    public Coordinator() {
        super();
        setRoleId("25-101");
    }
    
    public Coordinator(String username, String password, String email, String coordinatorName, String coordinatorDepartment) {
        super(username, password, email, "25-101");
        this.coordinatorName = coordinatorName;
        this.coordinatorDepartment = coordinatorDepartment;
    }

    public String getCoordinatorName() { return coordinatorName; }
    public void setCoordinatorName(String coordinatorName) { this.coordinatorName = coordinatorName; }

    public String getCoordinatorDepartment() { return coordinatorDepartment; }
    public void setCoordinatorDepartment(String coordinatorDepartment) { this.coordinatorDepartment = coordinatorDepartment; }

    public Long getCoordinatorID() { return super.getId(); }
}