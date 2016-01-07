package com.epam.restassured.pageobjects;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Base class for page objects.
 *
 * @author Peter_Olah1
 */
public abstract class AbstractBasePage {
    private WebDriver driver;

    protected AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    //TODO wrap WebElemwnt
    protected void fillField(WebElement element, String fieldValue) {
        Optional.ofNullable(element).ifPresent(webElement -> {
            webElement.clear();
            webElement.sendKeys(fieldValue);
        });
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
