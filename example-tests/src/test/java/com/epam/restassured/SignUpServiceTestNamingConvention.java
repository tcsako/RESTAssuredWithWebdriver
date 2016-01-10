package com.epam.restassured;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import com.epam.restassured.csvreader.model.CSVRestTestInputModel;
import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.service.client.SignUpServiceClient;
import com.epam.restassured.service.client.SubscriberServiceClient;
import com.google.common.collect.ImmutableList;

public class SignUpServiceTestNamingConvention {
    private static final Logger LOG = LogManager.getLogger(SignUpServiceTestNamingConvention.class);
    private static final int NUMBER_OF_RESPONSE = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";
    private static final String CONTENT_EMAIL_ADDRESS = "content.emailAddress";

    private static final String DEFAULT_TEST_INPUT_FILE = "test_data_rest.csv";
    private static final List<String> DEFAULT_TEST_PARAMETERS = ImmutableList.of("firstName", "lastName", "emailAddress", "emailAddressConfirmation", "newsletterOptIn");

    private static SignUpModel signUpModel;
    private static SignUpServiceClient signUpServiceClient;
    private static SubscriberServiceClient subscriberServiceClient;

    @BeforeClass
    public static void setUpBeforeClass() throws TestExecutionException {
    	LOG.info("Starting before test class");
    	
        final List<CSVRestTestInputModel> testData = CSVReaderUtilitySingleton.getInstance().getIntput(DEFAULT_TEST_INPUT_FILE, DEFAULT_TEST_PARAMETERS);
        if (!testData.isEmpty()) {
            CSVRestTestInputModel testInput = testData.get(0);
            signUpModel = SignUpModel.builder()
                    .firstName(testInput.getFirstName())
                    .lastName(testInput.getLastName())
                    .email(testInput.getEmailAddress())
                    .emailConfirmation(testInput.getEmailAddressConfirmation())
                    .wantNewsletters(testInput.isNewsletterOptIn())
                    .build();
        }
        
    	LOG.info("Before test class is finished");
    }
    
    @Before
    public void setUp() throws TestExecutionException {
    	LOG.info("Starting before to delete all existing subsribers");

        subscriberServiceClient = new SubscriberServiceClient();
        subscriberServiceClient.deleteSubscribers();
        signUpServiceClient = new SignUpServiceClient();

        LOG.info("Before is finished");
    }

    /**
     * Test class to add one record using MethodName_StateUnderTest_ExpectedBehavior naming convention
     * @throws TestExecutionException
     */
    @Test
    public void signUp_ValidUser_Successful() throws TestExecutionException {
    	LOG.info("Sign up with: " + signUpModel.toString());
        signUpServiceClient.signUp(signUpModel);

        LOG.info("Verify subsriber");
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
    	LOG.info("Verification is finished");
    }

    /**
     * Test class to add one record using MethodName_ExpectedBehavior_StateUnderTest naming convention
     * @throws TestExecutionException
     */
    @Test
    public void signUp_Successful_ValidUser() throws TestExecutionException {
    	LOG.info("Sign up with: " + signUpModel.toString());
        signUpServiceClient.signUp(signUpModel);
    	LOG.info("Verify subsriber");
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
    	LOG.info("Verification is finished");
    }

    /**
     * Test class to add one record using test[Feature being tested] naming convention
     * @throws TestExecutionException
     */
    @Test
    public void testUserCanSubsribe() throws TestExecutionException {
    	LOG.info("Sign up with: " + signUpModel.toString());
        signUpServiceClient.signUp(signUpModel);
    	LOG.info("Verify subsriber");
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
    	LOG.info("Verification is finished");
    }

    /**
     * Test class to add one record using Feature to be tested naming convention
     * @throws TestExecutionException
     */
    @Test
    public void userCanSubsribe() throws TestExecutionException {
    	LOG.info("Sign up with: " + signUpModel.toString());
        signUpServiceClient.signUp(signUpModel);
    	LOG.info("Verify subsriber");
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
    	LOG.info("Verification is finished");
    }

    /**
     * Test class to add one record using Should_ExpectedBehavior_When_StateUnderTest naming convention
     * @throws TestExecutionException
     */
    @Test
    public void should_Subsribe_When_ValidUser() throws TestExecutionException {
    	LOG.info("Sign up with: " + signUpModel.toString());
        signUpServiceClient.signUp(signUpModel);
    	LOG.info("Verify subsriber");
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
    	LOG.info("Verification is finished");
    }

    /**
     * Test class to add one record using When_StateUnderTest_Expect_ExpectedBehavior naming convention
     * @throws TestExecutionException
     */
    @Test
    public void when_ValidUser_Expect_Subsribe() throws TestExecutionException {
    	LOG.info("Sign up with: " + signUpModel.toString());
        signUpServiceClient.signUp(signUpModel);
    	LOG.info("Verify subsriber");
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
    	LOG.info("Verification is finished");
    }

    /**
     * Test class to add one record using Given_Preconditions_When_StateUnderTest_Then_ExpectedBehavior naming convention
     * @throws TestExecutionException
     */
    @Test
    public void given_DBIsClear_When_ValidUser_Then_Subsribe() throws TestExecutionException {
    	LOG.info("Sign up with: " + signUpModel.toString());
        signUpServiceClient.signUp(signUpModel);
    	LOG.info("Verify subsriber");
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(NUMBER_OF_RESPONSE)))
                .and().content(CONTENT_EMAIL_ADDRESS, contains(signUpModel.getEmail()));
    	LOG.info("Verification is finished");
    }
}