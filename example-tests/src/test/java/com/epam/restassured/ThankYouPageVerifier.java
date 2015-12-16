package com.epam.restassured;

import com.epam.restassured.pageobjects.ThankYouPageObject;
import com.epam.restassured.util.AbstractDriver;
import com.google.common.base.Verify;
import org.openqa.selenium.WebDriver;

/**
 *  Contains verification methods of {@ThankYouPageObject} the page.
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class ThankYouPageVerifier extends AbstractDriver {
    private ThankYouPageObject thanksPage = new ThankYouPageObject(getDriver());

    /**
     * Class constructor.
     *
     * @param driver {@link WebDriver}}
     */
    public ThankYouPageVerifier(WebDriver driver) {
        super(driver);
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
        Verify.verify(getDriver().getCurrentUrl().startsWith(
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
