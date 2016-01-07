package com.epam.restassured.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents a page object class with page elements, getters and setters.
 *
 * @author Peter_Olah1
 *
 */
public class ThankYouPagePageObject extends AbstractBasePage {

    @FindBy(css = "h1")
    private WebElement headerTitle;

    @FindBy(css = "h2")
    private WebElement headerSubtitle;

    /**
     * Class constructor.
     *
     * @param driver {@link WebDriver}.
     */
    public ThankYouPagePageObject(WebDriver driver) {
        super(driver);
    }

    public WebElement getHeaderSubtitle() {
        return headerSubtitle;
    }

    public WebElement getHeaderTitle() {
        return headerTitle;
    }

}