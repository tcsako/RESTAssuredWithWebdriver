package com.epam.restassured.webdriver.widgets;

import org.openqa.selenium.WebElement;

import com.epam.restassured.webdriver.base.ElementImpl;

public class TextElementImpl extends ElementImpl implements TextElement {

	public TextElementImpl(WebElement element) {
		super(element);
	}

	@Override
	public boolean isPresentedOnUi() {
		return getWrappedElement().isDisplayed();
	}

	@Override
	public String getText() {
		return getWrappedElement().getText();
	}

}
