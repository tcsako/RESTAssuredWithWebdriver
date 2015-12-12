package com.epam.restassured.pojo.csv;

/**
 * POJO to handle test input data come from CSV file.
 * 
 * @author Tamas_Csako
 *
 */
public class CSVTestInput {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String emailAddressConfirmation;
	private boolean newsletterOptIn;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddressConfirmation() {
		return emailAddressConfirmation;
	}
	public void setEmailAddressConfirmation(String emailAddressConfirmation) {
		this.emailAddressConfirmation = emailAddressConfirmation;
	}
	public boolean isNewsletterOptIn() {
		return newsletterOptIn;
	}
	public void setNewsletterOptIn(boolean newsletterOptIn) {
		this.newsletterOptIn = newsletterOptIn;
	}
}
