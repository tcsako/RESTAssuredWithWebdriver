package com.epam.restassured.webdriver.widgets;

import com.epam.restassured.webdriver.base.Element;
import com.epam.restassured.webdriver.base.ImplementedBy;

@ImplementedBy(CheckBoxImpl.class)
public interface CheckBox extends Element {
	void toggle();
	void check();
	void uncheck();
	boolean isChecked();
}
