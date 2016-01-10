package com.epam.restassured.pageobjects.verifier;

import com.epam.restassured.pageobjects.SignUpConfirmationPageObject;
import com.google.common.base.Verify;

/**
 * Contains verification methods for the {@ThankYouPagePageObject} page.
 *
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class SignUpConfirmationPageVerifier {
	private SignUpConfirmationPageObject signUpConfirmationPageObject;

	/**
	 * Class constructor.
	 *
	 */
	public SignUpConfirmationPageVerifier(SignUpConfirmationPageObject signUpConfirmationPageObject) {
		this.signUpConfirmationPageObject = signUpConfirmationPageObject;
	}

	/**
	 * Verifies that the page sub header contains the user's email address.
	 *
	 * @param firstName
	 *            The displayed first name.
	 * @param email
	 *            The displayed email address.
	 */
	public void thenSubscribeFinishedCheckDataOnPage(String firstName, String email) {
		Verify.verify(signUpConfirmationPageObject.getUrl().startsWith("https://t7-f0x.rhcloud.com/subscription/thank-you.html?"));
		isHeaderContainsFirstName(firstName);
		isSubTitleContainsEmail(email);
	}

	/**
	 * Verifies that the page header contains the user's first name.
	 *
	 * @param firstName
	 *            The user's first name.
	 */
	private void isHeaderContainsFirstName(String firstName) {
		String expectedTextValue = "Thank you " + firstName + "!";
		Verify.verify(expectedTextValue.equals(signUpConfirmationPageObject.getHeaderTitle().getText()));
	}

	/**
	 * Verifies that is the page sub header contains the user's email address.
	 *
	 * @param email
	 *            The user's email address.
	 */
	private void isSubTitleContainsEmail(String email) {
		Verify.verify(signUpConfirmationPageObject.getHeaderSubtitle().getText().contains(email));
	}
}
