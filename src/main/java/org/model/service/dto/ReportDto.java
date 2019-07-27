package org.model.service.dto;

public class ReportDto {
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

    private ReportDto() {
    }

    public static Builder builder() {
        return new ReportDto.Builder();
    }

    public static class Builder {
        private ReportDto reportDto = new ReportDto();

        private Builder() {
        }

        public Builder setCreationTime(String creationTime) {
            reportDto.setCreationTime(creationTime);
            return this;
        }

        public Builder setAcceptTime(String acceptTime) {
            reportDto.setAcceptTime(acceptTime);
            return this;
        }

        public Builder setAccepted(Boolean accepted) {
            reportDto.setAccepted(accepted);
            return this;
        }

        public Builder setAssessed(Boolean assessed) {
            reportDto.setAssessed(assessed);
            return this;
        }

        public Builder setPayerID(Long payerID) {
            reportDto.setPayerID(payerID);
            return this;
        }

        public Builder setOfficerID(Long officerID) {
            reportDto.setOfficerID(officerID);
            return this;
        }

        public Builder setPayerName(String payerName) {
            reportDto.setPayerName(payerName);
            return this;
        }

        public Builder setNote(String note) {
            reportDto.setNote(note);
            return this;
        }
        public ReportDto build() {
            return reportDto;
        }
    }


}
