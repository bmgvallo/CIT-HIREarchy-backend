package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

    @Entity
    public class Application {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long applicationID;

        private LocalDate applyDate;
        private String status;
        private String feedback;

        @ManyToOne
        @JoinColumn(name = "listingID")
        @JsonIgnore
        private InternshipListing internshipListing;

        @ManyToOne
        @JoinColumn(name = "studID")
        private Student student;

        public Long getApplicationID() { return applicationID; }
        public void setApplicationID(Long applicationID) { this.applicationID = applicationID; }

        public LocalDate getApplyDate() { return applyDate; }
        public void setApplyDate(LocalDate applyDate) { this.applyDate = applyDate; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getFeedback() { return feedback; }
        public void setFeedback(String feedback) { this.feedback = feedback; }

        public InternshipListing getInternshipListing() { return internshipListing; }
        public void setInternshipListing(InternshipListing internshipListing) { this.internshipListing = internshipListing; }

        public Student getStudent() { return student; }
        public void setStudent(Student student) { this.student = student; }
    }
