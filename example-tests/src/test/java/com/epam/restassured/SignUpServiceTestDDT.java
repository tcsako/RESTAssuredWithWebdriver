package com.epam.restassured;

import com.epam.restassured.csvreader.CSVReaderUtilitySingleton;
import com.epam.restassured.csvreader.model.CSVRestTestInputModel;
import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.service.client.SignUpServiceClient;
import com.epam.restassured.service.client.SubscriberServiceClient;
import com.google.common.collect.ImmutableList;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class SignUpServiceTestDDT {
    private static final Logger LOG = LogManager.getLogger(SignUpServiceTestDDT.class);
    private static final String DEFAULT_TEST_INPUT_FILE = "test_data_rest_ddt.csv";
    private static final List<String> DEFAULT_TEST_PARAMETERS = ImmutableList.of("firstName", "lastName", "emailAddress", "emailAddressConfirmation", "newsletterOptIn");
    private static final int EXPECTED_FILTEREDRESULT_NAME = 1;
    private static final String CONTENT_NUMBER_OF_ELEMENTS = "numberOfElements";

    @Parameterized.Parameter
    public int expectedNumberOfElements;

    @Parameterized.Parameter(1)
    public SignUpModel signUpModel;

    private SignUpServiceClient signUpServiceClient;
    private SubscriberServiceClient subscriberServiceClient;

    /**
     * Sets up the expected and actual parameters for the DDT methods.
     *
     * @return A 2 dimension array.
     * @throws TestExecutionException
     */
    @Parameterized.Parameters
    public static Collection<Object[]> testDataSet() throws TestExecutionException {
    	LOG.info("Setting up test data for DDT");

    	final List<CSVRestTestInputModel> testData = CSVReaderUtilitySingleton.getInstance().getIntput(DEFAULT_TEST_INPUT_FILE, DEFAULT_TEST_PARAMETERS);

        List<SignUpModel> signUpModels = null;

        if (!testData.isEmpty()) {
            signUpModels = new ArrayList<SignUpModel>();
            for (CSVRestTestInputModel testInput : testData) {
                signUpModels.add(SignUpModel.builder()
                        .firstName(testInput.getFirstName())
                        .lastName(testInput.getLastName())
                        .email(testInput.getEmailAddress())
                        .emailConfirmation(testInput.getEmailAddressConfirmation())
                        .wantNewsletters(testInput.isNewsletterOptIn())
                        .build());
            }
        }

    	LOG.info("Test data has been set up");

    	return Arrays.asList(new Object[][]{
                {1, signUpModels.get(0)},
                {2, signUpModels.get(1)},
                {3, signUpModels.get(2)},
                {4, signUpModels.get(3)},
                {5, signUpModels.get(4)}
        });
    }

    @BeforeClass
    public static void setUpBeforeClass() throws TestExecutionException {
    	LOG.info("Starting before test class to delete all existing subsribers");

    	new SubscriberServiceClient().deleteSubscribers();
    	
    	LOG.info("Before test class is finished");
    }

    @Before
    public void setUp() throws TestExecutionException {
    	LOG.info("Starting before to initialize service objects");

    	subscriberServiceClient = new SubscriberServiceClient();
        signUpServiceClient = new SignUpServiceClient();

        LOG.info("Before is finished");
    }

    @Test
    public void should_Subsribe_When_ValidUser() {
    	LOG.info("Sign up with: " + signUpModel.toString());
        signUpServiceClient.signUp(signUpModel);

    	LOG.info("Verify subsriber");
        subscriberServiceClient.searchFor(signUpModel.getFirstName())
                .then().statusCode(HttpStatus.SC_OK)
                .and().content("numberOfElements", is(EXPECTED_FILTEREDRESULT_NAME))
                .and().content("content.emailAddress", contains(signUpModel.getEmail()));
    	LOG.info("Verification is finished");
    }

    @Test
    public void should_VerifyNumberOfSubsribers_When_GetAllSubsribers() {
    	LOG.info("Verify number of subsribers");
        subscriberServiceClient.getSubscribers()
                .then().statusCode(HttpStatus.SC_OK)
                .and().content(CONTENT_NUMBER_OF_ELEMENTS, is(equalTo(expectedNumberOfElements)));
    	LOG.info("Verification is finished");
    }
}
