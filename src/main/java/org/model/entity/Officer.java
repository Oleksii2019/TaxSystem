package org.model.entity;

public class Officer extends User {

    private Officer() {
    }

    public static Builder builder() {
        return new Officer.Builder();
    }

    public static class Builder {
        private Officer officer = new Officer();

        private Builder() {
        }

        public Builder setLogin(String login) {
            officer.setLogin(login);
            return this;
        }

        public Builder setName(String name) {
            officer.setName(name);
            return this;
        }

        public Builder setPassword(String password) {
            officer.setPassword(password);
            return this;
        }

        public Builder setRole(int role) {
            officer.setRole(role);
            return this;
        }

        public Officer build() {
            return officer;
        }
    }

}
