package com.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class HomeSteps extends BaseSteps {

  @Given("the app is launched")
  public void theAppIsLaunched() {
    log.info("✅🛑 --- Verifying app is launched");
    assertTrue(homePage.verifyElementInTheHomePage(), "Home page is not displayed");
  }

  @When("I tap on the selected user")
  public void iEnterUsername() {
    log.info("Clicking in the selected user in the home page:");
    homePage.clickFirstUser();
  }
}
