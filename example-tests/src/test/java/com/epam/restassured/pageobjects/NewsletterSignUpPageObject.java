package com.epam.restassured.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.webdriver.factory.api.ElementFactory;
import com.epam.restassured.webdriver.widgets.Button;
import com.epam.restassured.webdriver.widgets.CheckBox;
import com.epam.restassured.webdriver.widgets.TextElement;
import com.epam.restassured.webdriver.widgets.TextInput;

/**
 * Contains page elements, and webpage interaction methods.
 *
 * @author Peter_Olah1
 */
public class NewsletterSignUpPageObject extends AbstractBasePage {

    @FindBy(id = "firstName")
    private TextInput firstName;

    @FindBy(id = "lastName")
    private TextInput lastName;

    @FindBy(css = "h1")
    private TextElement headerTitle;

    @FindBy(css = "h2")
    private TextElement subHeaderTitle;

    @FindBy(id = "emailAddress")
    private TextInput emailAddress;

    @FindBy(id = "emailAddressConfirmation")
    private TextInput confirmEmail;

    @FindBy(id = "newsletterOptIn1")
    private CheckBox newsLetterCheckBox;

    @FindBy(css = "input[type=\"submit\"]")
    private Button submitButton;

    public NewsletterSignUpPageObject(WebDriver driver) {
        super(driver);
        ElementFactory.initElements(getDriver(), this);
    }

    public void whenSignUp(SignUpModel model) {
        fillSignUpForm(model);
        submitButton.click();
    }

    private void fillSignUpForm(SignUpModel model) {
        firstName.set(model.getFirstName());
        lastName.set(model.getLastName());
        emailAddress.set(model.getEmail());
        confirmEmail.set(model.getEmailConfirmation());
        if (model.getWantNewsletters()) {
        	newsLetterCheckBox.check();
        } else  {
        	newsLetterCheckBox.uncheck();
        }
    }

    public TextElement getHeaderTitle() {
        return headerTitle;
    }

    public TextElement getSubHeaderTitle() {
        return subHeaderTitle;
    }

	public TextInput getFirstName() {
		return firstName;
	}

	public TextInput getLastName() {
		return lastName;
	}

	public TextInput getEmailAddress() {
		return emailAddress;
	}

	public TextInput getConfirmEmail() {
		return confirmEmail;
	}

	public CheckBox getNewsLetterCheckBox() {
		return newsLetterCheckBox;
	}

	public Button getSubmitButton() {
		return submitButton;
	}
    
}
