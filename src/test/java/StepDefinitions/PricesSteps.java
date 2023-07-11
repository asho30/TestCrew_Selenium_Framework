package StepDefinitions;

import java.awt.AWTException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import com.testcrew.testcases.TestCrew_Test;
import com.testcrew.base.TestBase;

public class PricesSteps extends TestBase {

	TestCrew_Test testCrew_Test= new TestCrew_Test();
	
	@Given("the user is on subscribe page")
	public void the_user_is_on_subscribe_page() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		testCrew_Test.setExtent();
		logger = extent.startTest(methodName);
		initialization(prop.getProperty("url")); // From config.properties
		testCrew_Test.pagesInitialization();
	}

	@And("has the subscription plans details {int}")
	public void has_the_subscription_plans_details_for(int countryNumber) throws IOException {
		testCrew_Test.readTestData(countryNumber);
	}

	@When("changes the country")
	public void changes_the_country() throws Exception {
		testCrew_Test.changeCountry();
	}

	@Then("validate the Price and Currency for each plan")
	public void validate_the_price_and_currency_for_each_plan() throws InterruptedException, AWTException {
		testCrew_Test.validateSubscriptionPlansDetails();
		testCrew_Test.endTest();
		testCrew_Test.endReport();
	}
}
