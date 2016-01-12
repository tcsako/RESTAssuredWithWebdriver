package com.epam.newsletter;

import com.epam.restassured.exception.TestExecutionException;
import com.epam.restassured.model.SignUpModel;
import com.epam.restassured.service.client.SignUpServiceClient;
import com.epam.restassured.service.client.SubscriberServiceClient;
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

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class SignUpServiceDDTHardCodedTestDataTest {

	private static final Logger LOG = LogManager.getLogger(SignUpServiceDDTHardCodedTestDataTest.class);
	private static final int EXPECTED_FILTEREDRESULT_NAME = 1;

	public int expectedNumberOfElements;
	public String firstName;
	public String lastName;
	public String email;
	public String emailConfirmation;
	public String wantNewsletters;

	private SignUpServiceClient signUpServiceClient;
	private SubscriberServiceClient subscriberServiceClient;

	/**
	 * Constructor to initialize parameters
	 * 
	 * @param expectedNumberOfElements
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param emailConfirmation
	 * @param wantNewsletters
	 */
	public SignUpServiceDDTHardCodedTestDataTest(
			int expectedNumberOfElements, String firstName, 
			String lastName, String email, 
			String emailConfirmation, String wantNewsletters) {
		this.expectedNumberOfElements = expectedNumberOfElements;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.emailConfirmation = emailConfirmation;
		this.wantNewsletters = wantNewsletters;
	}

	/**
	 * Sets up the expected and actual parameters for the DDT methods.
	 *
	 * @return A 2 dimension array.
	 * @throws TestExecutionException
	 */
	@Parameterized.Parameters
	public static Collection<Object[]> testDataSet() throws TestExecutionException {
		LOG.info("Setting up test data for DDT");
		return Arrays
				.asList(new Object[][] { { 1, "John", "Doe", "johndoe@freecloud.com", "johndoe@freecloud.com", "true" },
						{ 2, "Ronald", "Smith", "ronald@freecloud.com", "ronald@freecloud.com", "true" } });
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
		LOG.info("Sign up with user and verify subscriber");
		signUpServiceClient.signUp(SignUpModel.builder().firstName(firstName).lastName(lastName).email(email)
				.emailConfirmation(emailConfirmation).wantNewsletters(Boolean.parseBoolean(wantNewsletters)).build());
		subscriberServiceClient.searchFor(email).then().statusCode(HttpStatus.SC_OK).and()
				.content("numberOfElements", is(equalTo(EXPECTED_FILTEREDRESULT_NAME))).and()
				.content("content.emailAddress", contains(email));
	}

	@Test
	public void should_VerifyNumberOfSubsribers_When_GetAllSubsribers() {
		LOG.info("Verify number of subsribers");
		subscriberServiceClient.getSubscribers().then().statusCode(HttpStatus.SC_OK).and().content("numberOfElements",
				is(equalTo(expectedNumberOfElements)));
		LOG.info("Verification is finished");
	}
}
