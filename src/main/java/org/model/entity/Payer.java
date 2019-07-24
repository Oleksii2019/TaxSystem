package org.model.entity;

import org.model.UserRole;

public class Payer extends User {
    private Officer officer;

    public Payer() {
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public static class Builder {
        private Payer payer;
//        private String login;
//        private String name;
//        private String password;
//        private UserRole role;
//        private Officer officer;

        public Builder() {
            payer = new Payer();
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

//    private Payer(Builder builder) {
//        super.setLogin(builder.login);
//        super.setName(builder.name);
//        super.setPassword(builder.password);
//        super.setRole(builder.role);
//        officer = builder.officer;
//    }

}
