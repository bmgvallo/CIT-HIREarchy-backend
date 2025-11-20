// src/main/java/com/cithirearchy/cithirearchy/dto/StatusUpdateRequest.java
package com.cithirearchy.cithirearchy.dto;

public class StatusUpdateRequest {
    private String status;
    private String feedback;
    
    // Constructors
    public StatusUpdateRequest() {}
    
    public StatusUpdateRequest(String status, String feedback) {
        this.status = status;
        this.feedback = feedback;
    }
    
    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}