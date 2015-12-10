package com.epam.restassured.pageobjects;

import com.epam.restassured.util.Driver;
import org.openqa.selenium.ElementNotVisibleException;
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

        @FindBy(css = "#lastName")
        private WebElement lastName;

        @FindBy(css = "#emailAddress")
        private WebElement emailAddress;

        @FindBy(css = "#emailAddressConfirmation")
        private WebElement confirmEmail;

        @FindBy(css = "#newsletterOptIn1")
        private WebElement newsLetterCheckBox;

        @FindBy(css = "input[type=\"submit\"]")
        private WebElement submitButton;

        /**
         * Class constructor
         *
         * @param driver - {@link WebDriver}.
         */
        public HomePageObject(WebDriver driver) {
            PageFactory.initElements(Driver.getDriver(), this);
        }


        /**
         * @param element An editable field.
         * @param fieldValue The value that you want to enter into the field.
         * @throws ElementNotVisibleException When the WebElement is not visible.
         * @throws InterruptedException When the thead's wait is interrupted.
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
         * @throws Exception
         */
        private void setCheckBoxValue(WebElement checkBox, boolean doYouWantToSelectCheckbox) {
            if (doYouWantToSelectCheckbox == true) {
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
         * @throws Exception When a field not visible or editable, or the page cannot be loaded.
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
         * @throws Exception When a field not visible or editable, or the page cannot be loaded.
         */
        public ThankYouPageObject givenSubcribeToNewsletter(String firstName, String lastName, String email,
                                                            String emailAgain,
                                                            Boolean newsLetterCheckbox) {

            signUp(firstName, lastName, email, emailAgain, newsLetterCheckbox);
            submitButton.click();
            return new ThankYouPageObject(Driver.getDriver());
        }
}
