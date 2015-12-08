package com.epam.restassured.pojo;

public class Content {
	private String uuid;
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String newsletterOptIn;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
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
	public String getNewsletterOptIn() {
		return newsletterOptIn;
	}
	public void setNewsletterOptIn(String newsletterOptIn) {
		this.newsletterOptIn = newsletterOptIn;
	}
}
