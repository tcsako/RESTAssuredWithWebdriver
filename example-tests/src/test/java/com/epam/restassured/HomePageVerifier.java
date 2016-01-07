package com.epam.restassured;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.epam.restassured.pageobjects.HomePageObject;
import com.epam.restassured.util.AbstractDriver;
import com.google.common.base.Verify;

/**
 * Created by Peter_Olah1 on 12/16/2015.
 */
public class HomePageVerifier extends AbstractDriver {
    private static final String BASE_URL = "https://t7-f0x.rhcloud.com/subscription/subscription.html";
    private HomePageObject homePage;

    /**
     *  Class constructor.
     *
     * @param driver {@link WebDriver}
     */
    public HomePageVerifier(WebDriver driver) {
        super(driver);
    }

    /**
     *  Opens home page and initializes WebElements.
     *
     * @return HomePage instance.
     */
    public HomePageObject getInstance() {
        getDriver().get(BASE_URL);
        HomePageObject homePage = PageFactory.initElements(getDriver(), HomePageObject.class);
        return homePage;
    }

    /**
     * Checks the header's and the sub-header's visibility and title on the home page.
     *
     */
    public void checkHomePageHeaders() {
        HomePageObject homePage = PageFactory.initElements(getDriver(), HomePageObject.class);
        Verify.verify(homePage.getHeaderTitle().isDisplayed() && homePage.getSubHeaderTitle().isDisplayed());
        Verify.verify("Sign up for our newsletter".equals(homePage.getHeaderTitle().getText()));
        Verify.verify("get instant updates on hot products and special deals".equals(homePage.getSubHeaderTitle()
                .getText()));
    }

    /**
     *  Checks the input fields availability and visibility.
     *
     */
    public void checkHomePageFields() {
        HomePageObject homePage = PageFactory.initElements(getDriver(), HomePageObject.class);
        Verify.verify(homePage.getFirstName().isDisplayed());
        Verify.verify(homePage.getEmailAddress().isDisplayed());
        Verify.verify(homePage.getSubmitButton().isDisplayed());
        Verify.verify(homePage.getSubmitButton().isEnabled());
    }

    /**
     * Assures that the two email fields' values are equals.
     *
     * @throws AssertionError When the addresses are different.
     */
    public void emailVerification() throws AssertionError {
        Verify.verify(homePage.getEmailAddress().getText().equals(homePage.getConfirmEmail().getText()));
    }
}
