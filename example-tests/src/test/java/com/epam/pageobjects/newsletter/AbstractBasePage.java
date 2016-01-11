package com.epam.pageobjects.newsletter;

import org.openqa.selenium.WebDriver;

/**
 * Base class for page objects.
 *
 * @author Peter_Olah1
 */
public abstract class AbstractBasePage {
    private WebDriver driver;

    protected AbstractBasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
