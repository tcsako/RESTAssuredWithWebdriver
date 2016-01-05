package com.epam.restassured.pageobjects;

import com.google.common.base.Verify;

/**
 *  Contains verification methods of {@ThankYouPageObject} the page.
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class ThankYouPageVerifier{
    private ThankYouPagePageObject thanksPage;

    /**
     * Class constructor.
     *
     */
    public ThankYouPageVerifier(ThankYouPagePageObject thanksPage) {
        this.thanksPage= thanksPage;
    }

    /**
     *  Verifies that is the page sub header contains the user's email address.
     *
     * @param email The user's email address.
     *
     */
    private void isSubTitleContainsEmail(String email) {
         Verify.verify(thanksPage.getHeaderSubtitle().getText().contains(email));
    }

    /**
     * Verifies that is the page sub header containing the user's email address.
     *
     * @param firstName The displayed first name.
     * @param email The displayed email address.
     */
    public void whenSubscribeFinishedCheckDataOnPage(String firstName, String email) {
        Verify.verify(thanksPage.getDriver().getCurrentUrl().startsWith(
                "https://t7-f0x.rhcloud.com/subscription/thank-you.html?"));
        isHeaderContainsFirstName(firstName);
        isSubTitleContainsEmail(email);
    }

    /**
     *  Verifies that is the page header containing the user's first name.
     *
     * @param firstName The user's first name.
     */
    private void isHeaderContainsFirstName(String firstName) {
        Verify.verify(thanksPage.getHeaderTitle().getText()
                .equals("Thank you " + firstName + "!"));
    }

}
