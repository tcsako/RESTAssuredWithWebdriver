package com.epam.restassured.model;

import java.util.Objects;

/**
 * Model for sign up data.
 *
 * @author Jozsef_Koza
 */
public final class SignUpModel {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String emailConfirmation;
    private final Boolean wantNewsletters;

    private SignUpModel(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        emailConfirmation = builder.emailConfirmation;
        wantNewsletters = builder.wantNewsletters;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailConfirmation() {
        return emailConfirmation;
    }

    public Boolean getWantNewsletters() {
        return wantNewsletters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, emailConfirmation, wantNewsletters);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass() || this.hashCode() != obj.hashCode()) {
            return false;
        }
        SignUpModel other = (SignUpModel) obj;
        return Objects.equals(this.firstName, other.firstName)
                && Objects.equals(this.lastName, other.lastName)
                && Objects.equals(this.email, other.email)
                && Objects.equals(this.emailConfirmation, other.emailConfirmation)
                && Objects.equals(this.wantNewsletters, other.wantNewsletters);
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String emailConfirmation;
        private Boolean wantNewsletters;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder emailConfirmation(String emailConfirmation) {
            this.emailConfirmation = emailConfirmation;
            return this;
        }

        public Builder wantNewsletters(boolean wantNewsletters) {
            this.wantNewsletters = wantNewsletters;
            return this;
        }

        public SignUpModel build() {
            return new SignUpModel(this);
        }
    }
}
