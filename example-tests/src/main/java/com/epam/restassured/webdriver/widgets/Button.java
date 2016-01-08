package com.epam.restassured.webdriver.widgets;

import com.epam.restassured.webdriver.base.Element;
import com.epam.restassured.webdriver.base.ImplementedBy;

@ImplementedBy(ButtonImpl.class)
public interface Button extends Element {

	void clickButton();
}
