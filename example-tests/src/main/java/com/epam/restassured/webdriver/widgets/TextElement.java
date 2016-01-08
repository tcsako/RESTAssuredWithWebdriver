package com.epam.restassured.webdriver.widgets;

import com.epam.restassured.webdriver.base.Element;
import com.epam.restassured.webdriver.base.ImplementedBy;

@ImplementedBy(TextElementImpl.class)
public interface TextElement extends Element {
	boolean isPresentedOnUi();
	String getText();
}
