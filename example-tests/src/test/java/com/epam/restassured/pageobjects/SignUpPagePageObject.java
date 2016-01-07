package com.epam.restassured.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.restassured.model.SignUpModel;
import com.google.common.base.Verify;

/**
 * Contains page elements, and webpage interaction methods.
 *
 * @author Peter_Olah1
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

    public SignUpPagePageObject(WebDriver driver) {
        super(driver);
    }

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
     * Verifies that the subscription page elements are visible and enabled.
     */
    public void signUpPageFieldDisplayTest() {
        Verify.verify(firstName.isDisplayed());
        Verify.verify(lastName.isDisplayed());
        Verify.verify(emailAddress.isDisplayed());
        Verify.verify(submitButton.isDisplayed());
    }

    public void signUp(SignUpModel model) {
        fillSignUpForm(model);
        submitButton.click();
    }

    private void fillSignUpForm(SignUpModel model) {
        fillField(this.firstName, model.getFirstName());
        fillField(this.lastName, model.getLastName());
        fillField(this.emailAddress, model.getEmail());
        fillField(this.confirmEmail, model.getEmailConfirmation());
        setCheckBoxValue(this.newsLetterCheckBox, model.getWantNewsletters());
    }

    public WebElement getHeaderTitle() {
        return headerTitle;
    }

    public WebElement getSubHeaderTitle() {
        return subHeaderTitle;
    }
}
