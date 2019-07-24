package org.model.entity;

import org.model.UserRole;

public class Payer extends User {
    private Officer officer;

    public Officer getOfficer() {
        return officer;
    }
    public void setOfficer(Officer officer) {
        this.officer = officer;
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

        public Builder setRole(UserRole role) {
            payer.setRole(role);
            return this;
        }

        public Builder setOfficer(Officer officer) {
            payer.setOfficer(officer);
            return this;
        }

        public Payer build() {
            return payer;
        }
    }

}
