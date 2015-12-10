package com.epam.restassured.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class Driver {
    private static WebDriver driver;

    /**
     * Class constructor.
     */
    static {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    /**
     * @return the {@link WebDriver}.
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     *  Quits the driver. Closes every associated window.
     */
    public static void tearDown() {
        if (Driver.getDriver() != null) {
            Driver.getDriver().quit();
        }
    }
}
