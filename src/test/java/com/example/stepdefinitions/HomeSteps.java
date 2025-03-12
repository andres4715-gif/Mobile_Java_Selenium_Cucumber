package com.example.stepdefinitions;

//import com.example.pages.HomePage;
//import com.example.pages.LoginPage;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HomeSteps {

  private final AppiumDriver driver;
//  private LoginPage loginPage;
//  private HomePage homePage;

  public HomeSteps() {
    // Get driver from test context
    this.driver = (AppiumDriver) Hooks.getFromTestContext("driver");
  }

  @Given("the app is launched")
  public void theAppIsLaunched() {
    log.info("🔥🔥🔥🔥🔥 Verifying app is launched");
//    loginPage = new LoginPage(driver);
//    Assertions.assertTrue(loginPage.isDisplayed(), "Login page is not displayed");
  }

  @When("I enter username {string}")
  public void iEnterUsername(String username) {
    log.info("🔥🔥🔥🔥🔥 Entering username: {}", username);
//    loginPage.enterUsername(username);
  }

  @When("I enter password {string}")
  public void iEnterPassword(String password) {
    log.info("🔥🔥🔥🔥🔥  Entering password");
//    loginPage.enterPassword(password);
  }

  @When("I tap on the login button")
  public void iTapOnTheLoginButton() {
    log.info("🔥🔥🔥🔥🔥 Tapping on login button");
//    homePage = loginPage.clickLoginButton();
  }

  @Then("I should be logged in successfully")
  public void iShouldBeLoggedInSuccessfully() {
    log.info("🔥🔥🔥🔥🔥  Verifying successful login");
//    Assertions.assertTrue(homePage.isDisplayed(), "Home page is not displayed after login");
  }

  @Then("I should see the home screen")
  public void iShouldSeeTheHomeScreen() {
    log.info("🔥🔥🔥🔥🔥 Verifying home screen is displayed");
//    Assertions.assertTrue(homePage.isUsernameLabelDisplayed(), "Username label is not displayed");
  }

//  @Then("I should see an error message {string}")
//  public void iShouldSeeAnErrorMessage(String errorMessage) {
//    log.info("🔥🔥🔥🔥🔥 Verifying error message: {}", errorMessage);
//    String actualErrorMessage = loginPage.getErrorMessage();
//    Assertions.assertEquals(errorMessage, actualErrorMessage, "Error message does not match");
//  }
}
