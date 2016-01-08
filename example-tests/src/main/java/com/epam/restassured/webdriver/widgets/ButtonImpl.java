package com.epam.restassured.webdriver.widgets;

import org.openqa.selenium.WebElement;

import com.epam.restassured.webdriver.base.ElementImpl;

public class ButtonImpl extends ElementImpl implements Button {

	public ButtonImpl(WebElement element) {
		super(element);
	}
	
    @Override
	public void clickButton() {
		getLog().info("Clicking element " + this.toString());
		getWrappedElement().click();
	}

}
