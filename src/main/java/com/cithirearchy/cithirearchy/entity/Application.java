package com.cithirearchy.cithirearchy.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//test commit in different checkout.
    @Entity
    public class Application {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long applicationID;

        private LocalDate applyDate;
        private String status;
        private String feedback;
        private String coverLetter;
        private String resumeURL;

        @ManyToOne
        @JoinColumn(name = "listingID")
        @JsonIgnoreProperties({"applications"})
        private InternshipListing internshipListing;

        @ManyToOne
        @JoinColumn(name = "studID")
        @JsonIgnoreProperties({"applications"})
        private Student student;

        public Long getApplicationID() { return applicationID; }
        public void setApplicationID(Long applicationID) { this.applicationID = applicationID; }

        public LocalDate getApplyDate() { return applyDate; }
        public void setApplyDate(LocalDate applyDate) { this.applyDate = applyDate; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getFeedback() { return feedback; }
        public void setFeedback(String feedback) { this.feedback = feedback; }

        public String getCoverLetter() { return coverLetter; }
        public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

        public String getResumeURL() { return resumeURL; }
        public void setResumeURL(String resumeURL) { this.resumeURL = resumeURL; }

        public InternshipListing getInternshipListing() { return internshipListing; }
        public void setInternshipListing(InternshipListing internshipListing) { this.internshipListing = internshipListing; }

        public Student getStudent() { return student; }
        public void setStudent(Student student) { this.student = student; }
    }
