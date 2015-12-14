package com.epam.restassured.pojo;
/**
 * @author Tamas Csako
 *
 * Represents a data access object which represents user data.
 *
 */
public class Content {
    private String uuid;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String newsletterOptIn;

    /**
     * Getter for the user id.
     *
     * @return user ID
     */

    public String getUuid() {
        return uuid;
    }

    /**
     * Setter for the user id.
     *
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Getter for the email address.
     *
     * @return The user's email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter for the email address.
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter for the users first name.
     *
     * @return The actual first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the user's last name.
     *
     * @return The current last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for the user's last name.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the newsletter checkbox.
     *
     * @return The checkbox
     */
    public String getNewsletterOptIn() {
        return newsletterOptIn;
    }

    /**
     * Setter for the newsletter checkbox.
     *
     * @param newsletterOptIn
     */
    public void setNewsletterOptIn(String newsletterOptIn) {
        this.newsletterOptIn = newsletterOptIn;

    }
}
 
