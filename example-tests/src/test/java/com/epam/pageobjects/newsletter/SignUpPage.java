package com.epam.pageobjects.newsletter;

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
public class SignUpPage extends AbstractBasePage {

    @FindBy(id = "firstName")
    private TextInput firstName;

    @FindBy(id = "lastName")
    private TextInput lastName;

    @FindBy(id = "emailAddress")
    private TextInput emailAddress;

    @FindBy(id = "emailAddressConfirmation")
    private TextInput confirmEmail;

    @FindBy(id = "newsletterOptIn1")
    private CheckBox newsLetter;

    @FindBy(css = "input[type=\"submit\"]")
    private Button submit;

    @FindBy(css = "h1")
    private TextElement headerTitle;

    @FindBy(css = "h2")
    private TextElement subHeaderTitle;

    public SignUpPage(WebDriver driver) {
        super(driver);
        ElementFactory.initElements(getDriver(), this);
    }

    public void whenSignUp(SignUpModel model) {
        fillSignUpForm(model);
        submit.click();
    }

    private void fillSignUpForm(SignUpModel model) {
        firstName.set(model.getFirstName());
        lastName.set(model.getLastName());
        emailAddress.set(model.getEmail());
        confirmEmail.set(model.getEmailConfirmation());
        if (model.getWantNewsletters()) {
        	newsLetter.check();
        } else  {
        	newsLetter.uncheck();
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
		return newsLetter;
	}

	public Button getSubmitButton() {
		return submit;
	}
    
}
