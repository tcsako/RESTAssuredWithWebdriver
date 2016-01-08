package com.epam.restassured.webdriver.widgets;

import org.openqa.selenium.WebElement;

import com.epam.restassured.webdriver.base.ElementImpl;

public class CheckBoxImpl extends ElementImpl implements CheckBox {

	public CheckBoxImpl(WebElement element) {
		super(element);
	}

	@Override
	public void toggle() {
		getWrappedElement().click();
	}

	@Override
	public void check() {
		getLog().info("Checking checkbox element" + this.toString());
		if (!isChecked()) {
			toggle();
		}
	}

	@Override
	public void uncheck() {
		getLog().info("Uncheking checkbox element" + this.toString());
		if (isChecked()) {
			toggle();
		}
	}

	@Override
	public boolean isChecked() {
		return getWrappedElement().isSelected();
	}

}
