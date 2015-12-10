package com.epam.restassured.pageobjects;

import com.epam.restassured.util.Driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.google.common.base.Verify;

/**
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class ThankYouPageObject {

    @FindBy(css = "h1")
    private WebElement headerTitle;

    @FindBy(css = "h2")
    private WebElement headerSubtitle;

    /**
     * Class constructor.
     *
     * @param driver {@link WebDriver}.
     */
    public ThankYouPageObject(WebDriver driver) {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    /**
     * Verifies that is the page sub header containing the user's email address.
     */
    public void whenSubscribeFinishedCheckDataOnPage(String firstName, String email) {
        Verify.verify(Driver.getDriver().getCurrentUrl().toString()
                .startsWith("https://t7-f0x.rhcloud.com/subscription/thank-you.html?"));
        isHeaderContainsFirstName(firstName);
        isSubTitleContainsEmail(email);
    }

    /**
     *  Verifies that is the page header containing the user's first name.
     *
     * @param firstName The user's first name.
     */
    private void isHeaderContainsFirstName(String firstName) {
        Verify.verify(headerTitle.getText()
                .equals("Thank you " + firstName + "!"));
    }

    /**
     *  Verifies that is the page sub header contains the user's email address.
     *
     * @param email The user's email address.
     *
     */
    private void isSubTitleContainsEmail(String email) {
        Verify.verify(headerSubtitle.getText().toString().contains(email));
    }
}
