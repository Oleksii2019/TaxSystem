package org.model.entity;

import java.time.LocalDateTime;

public class Report {
    private Long id;
    private LocalDateTime creationTime;
    private LocalDateTime acceptTime;
    private Boolean accepted;
    private Boolean assessed;
    private Long payerID;
    private Long officerID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(LocalDateTime acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getAssessed() {
        return assessed;
    }

    public void setAssessed(Boolean assessed) {
        this.assessed = assessed;
    }

    public Long getPayerID() {
        return payerID;
    }

    public void setPayerID(Long payerID) {
        this.payerID = payerID;
    }

    public Long getOfficerID() {
        return officerID;
    }

    public void setOfficerID(Long officerID) {
        this.officerID = officerID;
    }

    private Report() {
    }

    public static Builder builder() {
        return new Report.Builder();
    }

    public static class Builder {
        private Report report = new Report();

        private Builder() {
        }

        public Builder setCreationTime(LocalDateTime creationTime) {
            report.setCreationTime(creationTime);
            return this;
        }

        public Builder setAcceptTime(LocalDateTime acceptTime) {
            report.setAcceptTime(acceptTime);
            return this;
        }

        public Builder setAccepted(Boolean accepted) {
            report.setAccepted(accepted);
            return this;
        }

        public Builder setAssessed(Boolean assessed) {
            report.setAssessed(assessed);
            return this;
        }

        public Builder setPayerID(Long payerID) {
            report.setPayerID(payerID);
            return this;
        }

        public Builder setOfficerID(Long officerID) {
            report.setOfficerID(officerID);
            return this;
        }

        public Report build() {
            return report;
        }
    }




}
