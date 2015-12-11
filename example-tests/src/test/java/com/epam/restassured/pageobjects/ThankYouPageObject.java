package com.epam.restassured.pageobjects;

import com.epam.restassured.util.AbstractDriver;
import com.google.common.base.Verify;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Contains page elements and interaction methods for the test class.
 *
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class ThankYouPageObject extends AbstractDriver {

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
        super(driver);
    }

    /**
     * @return HeaderSubtitle.
     */
    public WebElement getHeaderSubtitle() {
        return headerSubtitle;
    }

    /**
     * @return HeaderTitle.
     */
    public WebElement getHeaderTitle() {
        return headerTitle;
    }

    /**
     * Verifies that is the page sub header containing the user's email address.
     *
     * @param firstName The displayed first name.
     * @param email The displayed email address.
     */
    public void whenSubscribeFinishedCheckDataOnPage(String firstName, String email) {
        Verify.verify(getDriver().getCurrentUrl().startsWith("https://t7-f0x.rhcloud.com/subscription/thank-you.html?"));
        isHeaderContainsFirstName(firstName);
        isSubTitleContainsEmail(email);
    }

    /**
     * Verifies that is the page header containing the user's first name.
     *
     * @param firstName The user's first name.
     */
    private void isHeaderContainsFirstName(String firstName) {
        Verify.verify(headerTitle.getText()
                .equals("Thank you " + firstName + "!"));
    }

    /**
     * Verifies that is the page sub header contains the user's email address.
     *
     * @param email The user's email address.
     *
     */
    private void isSubTitleContainsEmail(String email) {
        Verify.verify(headerSubtitle.getText().contains(email));
    }
}
