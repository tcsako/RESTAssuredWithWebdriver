package com.epam.restassured.pageobjects;

import com.epam.restassured.util.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class HomePageObject {

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "#astName")
    private WebElement lastName;

    @FindBy(id = "emailAddress")
    private WebElement emailAddress;

    @FindBy(id = "emailAddressConfirmation")
    private WebElement confirmEmail;

    @FindBy(id = "newsletterOptIn1")
    private WebElement newsLetterCheckBox;

    @FindBy(css = "input[type=\"submit\"]")
    private WebElement submitButton;

    /**
     * Class constructor.
     *
     * @param driver - {@link WebDriver}.
     */
    public HomePageObject(WebDriver driver) {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    /**
     * @param element An editable field.
     * @param fieldValue The value that you want to enter into the field.
     */
    private void fillField(WebElement element, String fieldValue) {
        element.clear();
        element.sendKeys(fieldValue);
    }

    /**
     * Selects or deselects a checkbox.
     *
     * @param checkBox A checkbox WebElement.
     * @param doYouWantToSelectCheckbox True if you want to tick a checkbox, false if you want to de
     */
    private void setCheckBoxValue(WebElement checkBox, boolean doYouWantToSelectCheckbox) {
        if (doYouWantToSelectCheckbox) {
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        } else {
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        }
    }

    /**
     *  Fills the subcription data fields.
     *
     * @param firstName First name of the subscriber.
     * @param lastName Last name of the subscriber.
     * @param email Email address of the subscriber.
     * @param emailAgain Verification field for email field.
     * @param newsLetterCheckbox Newsletter checkbox.
     */
    private void signUp(String firstName, String lastName, String email, String emailAgain,
                        Boolean newsLetterCheckbox) {
        fillField(this.firstName, firstName);
        fillField(this.lastName, lastName);
        fillField(this.emailAddress, email);
        fillField(this.confirmEmail, emailAgain);
        setCheckBoxValue(this.newsLetterCheckBox, newsLetterCheckbox);
    }

    /**
     *  Makes a subscription with the given data fields and assures that the user is on the correct page.
     *
     * @param firstName First name of the subscriber.
     * @param lastName Last name of the subscriber.
     * @param email Email address of the subscriber.
     * @param emailAgain Verification field for email field.
     * @param newsLetterCheckbox Newsletter checkbox.
     */
    public ThankYouPageObject givenSignUp(String firstName, String lastName, String email,
                                          String emailAgain,
                                          Boolean newsLetterCheckbox) {

        signUp(firstName, lastName, email, emailAgain, newsLetterCheckbox);
        submitButton.click();
        return new ThankYouPageObject(Driver.getDriver());
    }
}
