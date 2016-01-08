package com.epam.restassured.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.epam.restassured.webdriver.factory.api.ElementFactory;
import com.epam.restassured.webdriver.widgets.TextElement;

/**
 * Represents a page object class with page elements, getters and setters.
 *
 * @author Peter_Olah1
 *
 */
public class SignUpConfirmationPageObject extends AbstractBasePage {

    @FindBy(css = "h1")
    private TextElement headerTitle;

    @FindBy(css = "h2")
    private TextElement headerSubtitle;

    /**
     * Class constructor.
     *
     * @param driver {@link WebDriver}.
     */
    public SignUpConfirmationPageObject(WebDriver driver) {
        super(driver);
        ElementFactory.initElements(getDriver(), this);
    }

    public TextElement getHeaderTitle() {
        return headerTitle;
    }
    
    public TextElement getHeaderSubtitle() {
        return headerSubtitle;
    }

    public String getUrl() {
    	return getDriver().getCurrentUrl();
    }

}