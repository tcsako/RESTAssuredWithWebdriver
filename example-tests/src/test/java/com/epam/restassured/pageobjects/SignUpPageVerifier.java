package com.epam.restassured.pageobjects;


import com.google.common.base.Verify;

/**
 * Represents verification methods on the sign up page.
 *
 * Created by Peter_Olah1 on 12/16/2015.
 */
public class SignUpPageVerifier {
    private SignUpPagePageObject signUpPage;

    /**
     *  Class constructor.
     *
     */
    public SignUpPageVerifier(SignUpPagePageObject signUpPage){
        this.signUpPage = signUpPage;
    }

    /**
     * Checks the header's and the sub-header's visibility and title on the home page.
     *thanksPage = new ThankYouPageObject(driver);
     */
    public void checkSignUpPageHeaders() {
        Verify.verify(signUpPage.getHeaderTitle().isDisplayed() && signUpPage.getSubHeaderTitle().isDisplayed());
        Verify.verify("Sign up for our newsletter".equals(signUpPage.getHeaderTitle().getText()));
        Verify.verify("get instant updates on hot products and special deals".equals(signUpPage.getSubHeaderTitle()
                .getText()));
    }

    /**
     *  Checks the input fields availability and visibility.
     *
     */
    public void checkSignUpPageFields() {
        signUpPage.signUpPageFieldDisplayTest();
    }
}
