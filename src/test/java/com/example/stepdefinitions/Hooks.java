package com.example.stepdefinitions;

import com.example.config.AppiumConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Log4j2
public class Hooks {
  private static final ThreadLocal<AppiumConfig> appiumConfig = ThreadLocal.withInitial(AppiumConfig::getInstance);
  private static final ThreadLocal<Object> driver = new ThreadLocal<>();

  @Before
  public void setUp(Scenario scenario) {
    log.info("Starting scenario: {}", scenario.getName());

    // Initialize driver
    appiumConfig.get().initializeDriver();
    driver.set(appiumConfig.get().getDriver());

    log.info("Driver initialized successfully");
  }

  @After
  public void tearDown(Scenario scenario) {
    log.info("Finishing scenario: {}", scenario.getName());

    // Take screenshot if scenario fails
    if (scenario.isFailed() && driver.get() != null) {
      log.info("Scenario failed, taking screenshot");
      TakesScreenshot screenshotDriver = (TakesScreenshot) driver.get();
      byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot, "image/png", "Screenshot on failure");
    }

    // Quit driver
    appiumConfig.get().quitDriver();
    driver.remove();

    log.info("Resources cleaned up successfully");
  }

  // Methods to get the driver in the secure way
  public static Object getFromTestContext() {
    return driver.get();
  }
}
