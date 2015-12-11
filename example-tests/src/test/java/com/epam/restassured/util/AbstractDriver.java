package com.epam.restassured.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class AbstractDriver {
    private WebDriver driver;

    /**
     * Class constructor.
     *
     * @param driver {@link WebDriver}
     */
    public AbstractDriver(WebDriver driver) {
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

