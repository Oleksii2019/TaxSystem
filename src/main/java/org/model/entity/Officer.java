package org.model.entity;

import org.model.UserRole;

public class Officer extends User {

    public static class Builder {
        private String login;
        private String name;
        private String password;
        private UserRole role;

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRole(UserRole role) {
            this.role = role;
            return this;
        }

        public Officer build() {
            return new Officer(this);
        }
    }

    private Officer(Builder builder) {
        super.setLogin(builder.login);
        super.setName(builder.name);
        super.setPassword(builder.password);
        super.setRole(builder.role);
    }

}
