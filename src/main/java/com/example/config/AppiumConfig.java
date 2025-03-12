package com.example.config;

import com.example.utils.AppiumDriverFactory;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public class AppiumConfig {
  private static AppiumConfig instance;
  private final Properties properties;
  private AppiumDriver driver;
  private final AppiumDriverFactory driverFactory;

  private AppiumConfig() {
    properties = new ConfigReader().getProperties();
    driverFactory = new AppiumDriverFactory();
  }

  public static synchronized AppiumConfig getInstance() {
    if (instance == null) {
      instance = new AppiumConfig();
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
    String platformName = properties.getProperty("platform.name");
    String automationName = properties.getProperty("automation.name");
    String appPath = properties.getProperty("app.path");
    String appPackage = properties.getProperty("app.package");
    String appActivity = properties.getProperty("app.activity");
    String deviceName = properties.getProperty("device.name");
    String udid = properties.getProperty("device.udid");

    log.info("Initializing Appium driver with platform: {}, device: {}", platformName, deviceName);

    if (platformName.equalsIgnoreCase("android")) {
      driver = driverFactory.createAndroidDriver(deviceName, udid, automationName, appPath, appPackage, appActivity);
    } else if (platformName.equalsIgnoreCase("ios")) {
      String bundleId = properties.getProperty("app.bundleId");
      driver = driverFactory.createIOSDriver(deviceName, udid, automationName, appPath, bundleId);
    } else {
      throw new IllegalArgumentException("Unsupported platform: " + platformName);
    }

    log.info("Appium driver initialized successfully");
  }

  public void quitDriver() {
    if (driver != null) {
      log.info("Quitting Appium driver");
      driver.quit();
      driver = null;
    }
  }
}
