package com.example.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

@Log4j2
public class AppiumDriverFactory {
  private static final String APPIUM_SERVER_URL = "http://localhost:4723";

  /**
   * Create Android driver with provided capabilities
   * @param deviceName name of device
   * @param udid UDID of device
   * @param automationName name of automation framework
   * @param appPath path to app
   * @param appPackage app package
   * @param appActivity app activity
   * @return AndroidDriver instance
   */
  public AndroidDriver createAndroidDriver(String deviceName, String udid, String automationName,
                                           String appPath, String appPackage, String appActivity) {
    try {
      log.info("Creating Android driver for device: {}, UDID: {}", deviceName, udid);

      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("platformName", "Android");
      capabilities.setCapability("deviceName", deviceName);

      if (udid != null && !udid.isEmpty()) {
        capabilities.setCapability("udid", udid);
      }

      capabilities.setCapability("automationName", automationName != null ? automationName : "UiAutomator2");

      if (appPath != null && !appPath.isEmpty()) {
        capabilities.setCapability("app", appPath);
      }

      if (appPackage != null && !appPackage.isEmpty()) {
        capabilities.setCapability("appPackage", appPackage);
      }

      if (appActivity != null && !appActivity.isEmpty()) {
        capabilities.setCapability("appActivity", appActivity);
      }

      capabilities.setCapability("autoGrantPermissions", true);
      capabilities.setCapability("noReset", false);
      capabilities.setCapability("newCommandTimeout", 300);

      URL url = new URL(APPIUM_SERVER_URL);
      AndroidDriver driver = new AndroidDriver(url, capabilities);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

      log.info("Android driver created successfully");
      return driver;

    } catch (Exception e) {
      log.error("Failed to create Android driver", e);
      throw new RuntimeException("Failed to create Android driver", e);
    }
  }

  /**
   * Create iOS driver with provided capabilities
   * @param deviceName name of device
   * @param udid UDID of device
   * @param automationName name of automation framework
   * @param appPath path to app
   * @param bundleId bundle identifier
   * @return IOSDriver instance
   */
  public IOSDriver createIOSDriver(String deviceName, String udid, String automationName,
                                   String appPath, String bundleId) {
    try {
      log.info("Creating iOS driver for device: {}, UDID: {}", deviceName, udid);

      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("platformName", "iOS");
      capabilities.setCapability("deviceName", deviceName);

      if (udid != null && !udid.isEmpty()) {
        capabilities.setCapability("udid", udid);
      }

      capabilities.setCapability("automationName", automationName != null ? automationName : "XCUITest");

      if (appPath != null && !appPath.isEmpty()) {
        capabilities.setCapability("app", appPath);
      }

      if (bundleId != null && !bundleId.isEmpty()) {
        capabilities.setCapability("bundleId", bundleId);
      }

      capabilities.setCapability("autoAcceptAlerts", true);
      capabilities.setCapability("noReset", false);
      capabilities.setCapability("newCommandTimeout", 300);

      URL url = new URL(APPIUM_SERVER_URL);
      IOSDriver driver = new IOSDriver(url, capabilities);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

      log.info("iOS driver created successfully");
      return driver;

    } catch (Exception e) {
      log.error("Failed to create iOS driver", e);
      throw new RuntimeException("Failed to create iOS driver", e);
    }
  }
}
