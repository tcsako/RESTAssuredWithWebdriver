package com.epam.restassured.pageobjects;

import com.google.common.base.Verify;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Contains page elements, and webpage interaction methorsd.
 * Created by Peter_Olah1 on 12/10/2015.
 */
public class SignUpPagePageObject extends AbstractBasePage {

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(css = "h1")
    private WebElement headerTitle;

    @FindBy(css = "h2")
    private WebElement subHeaderTitle;

    @FindBy(id = "emailAddress")
    private WebElement emailAddress;

    @FindBy(id = "emailAddressConfirmation")
    private WebElement confirmEmail;

    @FindBy(id = "newsletterOptIn1")
    private WebElement newsLetterCheckBox;

    @FindBy(css = "input[type=\"submit\"]")
    private WebElement submitButton;

    @FindBy(id = "form")
    private WebElement signUpForm;

    /**
     * Class constructor.
     *
     * @param driver - {@link WebDriver}.
     */
    public SignUpPagePageObject(WebDriver driver) {
        super(driver);
    }

    /**
     * Getter for web element.
     *
     * @return signUpForm
     */
    public WebElement getSignUpForm() {
        return signUpForm;
    }

    /**
     * Getter for web element.
     *
     * @return headerTitle
     */
    public WebElement getHeaderTitle() {
        return headerTitle;
    }

    /**
     * Getter for web element.
     *
     * @return headerTitle
     */
    public WebElement getSubHeaderTitle() {
        return subHeaderTitle;
    }

    /**
     * Getter for web element.
     *
     * @return firstName
     */
    public WebElement getFirstName() {
        return firstName;
    }

    /**
     * Getter for web element.
     *
     * @return lastName
     */
    public WebElement getLastName() {
        return lastName;
    }

    /**
     * Getter for web element.
     *
     * @return emailAddress
     */
    public WebElement getEmailAddress() {
        return emailAddress;
    }

    /**
     * Getter for web element.
     *
     * @return confirmEmail
     */

    /**
     * Getter for web element.
     *
     * @return submitButton
     */
    public WebElement getSubmitButton() {
        return submitButton;
    }

    /**
     * @param element An editable field.
     * @param fieldValue The value that you want to enter into the field.
     */
    private void fillField(WebElement element, String fieldValue) {
        //TODO WebElemwnt wrapperbe
        element.clear();
        element.sendKeys(fieldValue);
    }

    /**
     * Selects or deselects a checkbox.
     *
     * @param checkBox A checkbox WebElement.
     * @param doYouWantToSelectCheckbox True if you want to tick a checkbox, false if you want to de
     */
    //TODO WebElemwnt wrapperbe
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
     *  Verifies that the subscription page elements are visible and enabled.
     */
    public void signUpPageFieldDisplayTest(){
        Verify.verify(firstName.isDisplayed());
        Verify.verify(lastName.isDisplayed());
        Verify.verify(emailAddress.isDisplayed());
        Verify.verify(submitButton.isDisplayed());
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
     * @return ThankYouPage instance
     */
    public void givenSignUp(String firstName, String lastName, String email,
                                          String emailAgain,
                                          Boolean newsLetterCheckbox) {

        signUp(firstName, lastName, email, emailAgain, newsLetterCheckbox);
        submitButton.click();

    }
}
