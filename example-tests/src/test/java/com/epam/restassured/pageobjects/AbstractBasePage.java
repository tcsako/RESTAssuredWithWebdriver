package com.epam.restassured.pageobjects;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.epam.restassured.webdriver.factory.api.ElementFactory;

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
