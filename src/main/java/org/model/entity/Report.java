package org.model.entity;

public class Report {
    private Long id;
    private String creationTime;
    private String acceptTime;
    private Boolean accepted;
    private Boolean assessed;
    private Long payerID;
    private Long officerID;
    private String payerName;
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
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

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

        public Builder setId(Long id) {
            report.setId(id);
            return this;
        }

        public Builder setCreationTime(String creationTime) {
            report.setCreationTime(creationTime);
            return this;
        }

        public Builder setAcceptTime(String acceptTime) {
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

        public Builder setPayerName(String payerName) {
            report.setPayerName(payerName);
            return this;
        }

        public Builder setNote(String note) {
            report.setNote(note);
            return this;
        }

        public Report build() {
            return report;
        }
    }

}
