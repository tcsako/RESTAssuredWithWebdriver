package com.epam.restassured.webdriver.widgets;

import com.epam.restassured.webdriver.base.Element;
import com.epam.restassured.webdriver.base.ImplementedBy;

/**
 * Text field functionality.
 */
@ImplementedBy(TextInputImpl.class)
public interface TextInput extends Element {
    /**
     * @param text The text to type into the field.
     */
    void set(String text);
}
