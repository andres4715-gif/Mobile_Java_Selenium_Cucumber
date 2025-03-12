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
  private AppiumDriver driver;

  @Before
  public void setup(Scenario scenario) {
    log.info("Starting scenario: {}", scenario.getName());

    // Initialize driver
    driver = AppiumConfig.getInstance().getDriver();

    // Add driver to test context
    testContext.put("driver", driver);

    // Initialize scenario to test context
    testContext.put("scenario", scenario);

    log.info("Test setup completed successfully");
  }

  @After
  public void teardown(Scenario scenario) {
    log.info("Finishing scenario: {}", scenario.getName());

    if (scenario.isFailed() && driver != null) {
      log.info("Scenario failed, taking screenshot");
      try {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Screenshot-" + scenario.getName());
      } catch (Exception e) {
        log.error("Failed to take screenshot", e);
      }
    }

    // Don't quit the driver here to reuse it between scenarios
    // Only clear the context
    testContext.clear();

    log.info("Test teardown completed successfully");
  }

  @After(order = 100)
  public void finalTeardown() {
    // This runs after all scenarios as the last hook
    // Quit the driver here
    AppiumConfig.getInstance().quitDriver();
    log.info("Driver has been quit successfully");
  }

  /**
   * Get test context
   * @return test context map
   */
  public static Map<String, Object> getTestContext() {
    return testContext;
  }

  /**
   * Get value from test context
   * @param key key to get value for
   * @return value from test context
   */
  public static Object getFromTestContext(String key) {
    return testContext.get(key);
  }

  /**
   * Add value to test context
   * @param key key to add value for
   * @param value value to add
   */
  public static void addToTestContext(String key, Object value) {
    testContext.put(key, value);
  }
}
