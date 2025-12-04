package com.cithirearchy.cithirearchy.dto;

public class StatusUpdateRequest {
    private String status;
    private String feedback;
    
    public StatusUpdateRequest() {}
    
    public StatusUpdateRequest(String status, String feedback) {
        this.status = status;
        this.feedback = feedback;
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}