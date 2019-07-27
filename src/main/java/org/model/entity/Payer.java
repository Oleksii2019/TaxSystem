package org.model.entity;

public class Payer extends User {
    private Long officerID;

    public Long getOfficerID() {
        return officerID;
    }
    public void setOfficerID(Long officerID) {
        this.officerID = officerID;
    }

    private Payer() {
    }

    public static Builder builder() {
        return new Payer.Builder();
    }

    public static class Builder {
        private Payer payer = new Payer();

        private Builder() {
        }

        public Builder setLogin(String login) {
            payer.setLogin(login);
            return this;
        }

        public Builder setName(String name) {
            payer.setName(name);
            return this;
        }

        public Builder setPassword(String password) {
            payer.setPassword(password);
            return this;
        }

        public Builder setRole(int role) {
            payer.setRole(role);
            return this;
        }

        public Builder setOfficerID(Long officerID) {
            payer.setOfficerID(officerID);
            return this;
        }

        public Payer build() {
            return payer;
        }
    }

}
