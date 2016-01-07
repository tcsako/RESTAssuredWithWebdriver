package com.epam.restassured.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Peter_Olah1 on 12/21/2015.
 */
public abstract class AbstractBasePage {
    private WebDriver driver;

    /**
     * Class constructor.
     *
     * @param driver {@link WebDriver}
     */
    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /**
     * @return A {@link WebDriver}
     */
    public WebDriver getDriver() {
        return driver;
    }
}
