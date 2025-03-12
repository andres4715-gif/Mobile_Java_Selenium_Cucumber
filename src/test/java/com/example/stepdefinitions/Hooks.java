package com.example.stepdefinitions;

import com.example.config.AppiumConfig;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class Hooks {
  private static final Map<String, Object> testContext = new HashMap<>();
  private final AppiumConfig appiumConfig = AppiumConfig.getInstance();

  @Before
  public void setUp(Scenario scenario) {
    log.info("Starting scenario: {}", scenario.getName());

    // Initialize driver
    appiumConfig.initializeDriver();
    testContext.put("driver", appiumConfig.getDriver());

    log.info("Driver initialized successfully");
  }

  @After
  public void tearDown(Scenario scenario) {
    log.info("Finishing scenario: {}", scenario.getName());

    // Take screenshot if scenario fails
    if (scenario.isFailed() && testContext.containsKey("driver")) {
      log.info("Scenario failed, taking screenshot");
      TakesScreenshot driver = (TakesScreenshot) testContext.get("driver");
      byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot, "image/png", "Screenshot on failure");
    }

    // Quit driver
    appiumConfig.quitDriver();
    testContext.clear();

    log.info("Resources cleaned up successfully");
  }

  public static Object getFromTestContext(String key) {
    return testContext.get(key);
  }

  public static void putInTestContext(String key, Object value) {
    testContext.put(key, value);
  }
}
