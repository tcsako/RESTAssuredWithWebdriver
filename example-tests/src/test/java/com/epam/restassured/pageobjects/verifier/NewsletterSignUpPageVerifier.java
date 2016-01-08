package com.epam.restassured.pageobjects.verifier;

import com.epam.restassured.pageobjects.NewsletterSignUpPageObject;
import com.google.common.base.Verify;

/**
 * Represents verification methods on the sign up page.
 *
 * Created by Peter_Olah1 on 12/16/2015.
 */
public class NewsletterSignUpPageVerifier {
	private static final String EXPECTED_HEADER_TITLE_TEXT = "Sign up for our newsletter";
	private static final String EXPECTED_SUB_HEADER_TITLE_TEXT = "get instant updates on hot products and special deals";
	private NewsletterSignUpPageObject newsletterSignUpPageObject;

	/**
	 * Class constructor.
	 *
	 */
	public NewsletterSignUpPageVerifier(NewsletterSignUpPageObject newsletterSignUpPageObject) {
		this.newsletterSignUpPageObject = newsletterSignUpPageObject;
	}

	/**
	 * Checks the header's and the sub-header's visibility and title on the home
	 * page. thanksPage = new ThankYouPageObject(driver);
	 */
	public void givenSignUpPageHeadersDisplayed() {
		Verify.verify(newsletterSignUpPageObject.getHeaderTitle().isPresentedOnUi()
				&& newsletterSignUpPageObject.getSubHeaderTitle().isPresentedOnUi());
		Verify.verify(EXPECTED_HEADER_TITLE_TEXT.equals(newsletterSignUpPageObject.getHeaderTitle().getText()));
		Verify.verify(EXPECTED_SUB_HEADER_TITLE_TEXT.equals(newsletterSignUpPageObject.getSubHeaderTitle().getText()));
	}

	/**
	 * Checks the input fields availability and visibility.
	 *
	 */
	public void givenSignUpPageFieldsDisplayed() {
		Verify.verify(newsletterSignUpPageObject.getFirstName().isDisplayed());
		Verify.verify(newsletterSignUpPageObject.getLastName().isDisplayed());
		Verify.verify(newsletterSignUpPageObject.getEmailAddress().isDisplayed());
		Verify.verify(newsletterSignUpPageObject.getConfirmEmail().isDisplayed());
		Verify.verify(newsletterSignUpPageObject.getNewsLetterCheckBox().isDisplayed());
		Verify.verify(newsletterSignUpPageObject.getSubmitButton().isDisplayed());
	}
}
