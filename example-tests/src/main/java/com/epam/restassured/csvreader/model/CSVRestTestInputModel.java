package com.epam.restassured.csvreader.model;

/**
 * POJO to handle test input data come from CSV file.
 * 
 * @author Tamas_Csako
 *
 */
public class CSVRestTestInputModel {
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String emailAddressConfirmation;
	private boolean newsletterOptIn;

	/**
	 * Constructor to create object using builder pattern
	 * 
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param emailAddressConfirmation
	 * @param newsletterOptIn
	 */
	private CSVRestTestInputModel(CSVRestTestInputBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.emailAddress = builder.emailAddress;
		this.emailAddressConfirmation = builder.emailAddressConfirmation;
		this.newsletterOptIn = builder.newsletterOptIn;
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

	/**
	 * Inner class to implement builder pattern to set data
	 * 
	 * @author Tamas_Csako
	 *
	 */
	public static class CSVRestTestInputBuilder {
		private String firstName;
		private String lastName;
		private String emailAddress;
		private String emailAddressConfirmation;
		private boolean newsletterOptIn;

		public CSVRestTestInputBuilder() {
		}

		public CSVRestTestInputBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public CSVRestTestInputBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public CSVRestTestInputBuilder emailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}

		public CSVRestTestInputBuilder emailAddressConfirmation(String emailAddressConfirmation) {
			this.emailAddressConfirmation = emailAddressConfirmation;
			return this;
		}

		public CSVRestTestInputBuilder newsletterOptIn(boolean newsletterOptIn) {
			this.newsletterOptIn = newsletterOptIn;
			return this;
		}

		public CSVRestTestInputModel build() {
			return new CSVRestTestInputModel(this);
		}
	}

	@Override
	public String toString() {
		return "Test Input [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress
				+ ", emailAddressConfirmation=" + emailAddressConfirmation + ", newsletterOptIn=" + newsletterOptIn
				+ "]";
	}

}
