package com.example.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Log4j2
public class BrowserStackConfig {
  private static BrowserStackConfig instance;
  private final Properties properties;
  private AppiumDriver driver;

  private BrowserStackConfig() {
    ConfigReader configReader = new ConfigReader();
    properties = configReader.getPropertiesFromFile("browserstack.properties");
  }

  public static synchronized BrowserStackConfig getInstance() {
    if (instance == null) {
      instance = new BrowserStackConfig();
    }
    return instance;
  }

  public AppiumDriver getDriver() {
    if (driver == null) {
      initializeDriver();
    }
    return driver;
  }

  public void initializeDriver() {
    String username = properties.getProperty("browserstack.username");
    String accessKey = properties.getProperty("browserstack.accessKey");
    String appUrl = properties.getProperty("browserstack.app");
    String platformName = properties.getProperty("platform.name");

    log.info("Initializing BrowserStack driver for platform: {}", platformName);

    try {
      DesiredCapabilities capabilities = new DesiredCapabilities();

      // BrowserStack capabilities
      capabilities.setCapability("browserstack.user", username);
      capabilities.setCapability("browserstack.key", accessKey);
      capabilities.setCapability("app", appUrl);

      // Set device capabilities
      capabilities.setCapability("deviceName", properties.getProperty("device.name"));
      capabilities.setCapability("platformName", platformName);
      capabilities.setCapability("platformVersion", properties.getProperty("platform.version"));

      // BrowserStack specific capabilities
      capabilities.setCapability("project", properties.getProperty("browserstack.project"));
      capabilities.setCapability("build", properties.getProperty("browserstack.build"));
      capabilities.setCapability("name", properties.getProperty("browserstack.name"));

      // Set Debug capabilities
      Map<String, String> browserstackOptions = new HashMap<>();
      browserstackOptions.put("networkLogs", "true");
      browserstackOptions.put("deviceLogs", "true");
      capabilities.setCapability("bstack:options", browserstackOptions);

      // Create driver based on platform
      String browserstackURL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

      if (platformName.equalsIgnoreCase("android")) {
        capabilities.setCapability("appPackage", properties.getProperty("app.package"));
        capabilities.setCapability("appActivity", properties.getProperty("app.activity"));
        driver = new AndroidDriver(new URL(browserstackURL), capabilities);
      } else if (platformName.equalsIgnoreCase("ios")) {
        capabilities.setCapability("bundleId", properties.getProperty("app.bundleId"));
        driver = new IOSDriver(new URL(browserstackURL), capabilities);
      } else {
        throw new IllegalArgumentException("Unsupported platform: " + platformName);
      }

      log.info("BrowserStack driver initialized successfully");

    } catch (Exception e) {
      log.error("Failed to initialize BrowserStack driver", e);
      throw new RuntimeException("Failed to initialize BrowserStack driver", e);
    }
  }

  public void quitDriver() {
    if (driver != null) {
      log.info("Quitting BrowserStack driver");
      driver.quit();
      driver = null;
    }
  }
}
