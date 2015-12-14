package com.epam.restassured.pageobjects;

import com.epam.restassured.util.AbstractDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents a page object class with page elements, getters and setters.
 *
 *
 * @author Peter_Olah1
 *
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

}