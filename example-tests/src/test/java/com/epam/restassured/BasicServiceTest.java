package com.epam.restassured;

//TODO: 1. create basic webdriver script + proper page object pattern usage
//TODO: 2. create basic script to do the subscription with webdriver and verify with rest
//TODO: 3. create composition for subscription page with PO (using abstract factory)
//TODO: 4. cerate CSV reader with Singleton design pattern
//TODO: 5. create DDT script for REST script
//TODO: 6. create DDT script for webdriver script
//TODO: 7. create rest script without BDD style (using JUnit asssertions)

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.pojo.csv.CSVRestTestInput;
import com.google.common.collect.ImmutableList;

public class BasicServiceTest {
    private static final Logger LOG = Logger.getLogger(BasicServiceTest.class);

    private static final int NUMBER_OF_RESPONSE = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
    private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";

    private static final String DEFAULT_TEST_INPUT_FILE = "test_data_rest.csv";
    private static final List<String> DEFAULT_TEST_PARAMETERS = ImmutableList.of("firstName", "lastName", "emailAddress", "emailAddressConfirmation", "newsletterOptIn");

    private SignUpModel signUpModel;

    @Before
    public void setUp() throws TestExecutionException {
        LOG.info("Deleting existing records");
        if (given().delete(ServiceTestingProperties.REST_API_URL).getStatusCode() == HttpStatus.SC_OK) {
            LOG.info("Records were deleted successfully");
        } else {
            LOG.info("Something went wrong! Existing records couldn't be deleted");
        }

        final List<CSVRestTestInput> testData = CSVReaderUtilitySingleton.getInstance().getIntput(DEFAULT_TEST_INPUT_FILE, DEFAULT_TEST_PARAMETERS);
        if (!testData.isEmpty()) {
            CSVRestTestInput testInput = testData.get(0);
            signUpModel = SignUpModel.builder()
                    .firstName(testInput.getFirstName())
                    .lastName(testInput.getLastName())
                    .email(testInput.getEmailAddress())
                    .emailConfirmation(testInput.getEmailAddressConfirmation())
                    .wantNewslettes(testInput.isNewsletterOptIn())
                    .build();
        }
    }

    @Test
    public void addRecord() throws TestExecutionException {
        given().contentType(ServiceTestingProperties.JSON_CONTENT_TYPE).and()
                .post(ServiceTestingProperties.getUrlToPostData(signUpModel.getFirstName(), signUpModel.getLastName(),
                        signUpModel.getEmail(), signUpModel.getEmailConfirmation(),
                        String.valueOf(signUpModel.getWantNewsletters())));

        when().get(ServiceTestingProperties.REST_API_URL).
                then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
    }
}
