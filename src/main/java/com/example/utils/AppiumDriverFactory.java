package com.example.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.HttpCommandExecutor;

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

      UiAutomator2Options options = new UiAutomator2Options();
      options.setPlatformName("Android");
      options.setDeviceName(deviceName);

      if (udid != null && !udid.isEmpty()) {
        options.setUdid(udid);
      }

      options.setAutomationName(automationName != null ? automationName : "UiAutomator2");

      if (appPath != null && !appPath.isEmpty()) {
        options.setApp(appPath);
      }

      if (appPackage != null && !appPackage.isEmpty()) {
        options.setAppPackage(appPackage);
      }

      if (appActivity != null && !appActivity.isEmpty()) {
        options.setAppActivity(appActivity);
      }

      options.setAutoGrantPermissions(true);
      options.setNoReset(false);
      options.setNewCommandTimeout(Duration.ofSeconds(300));

      URL url = new URL(APPIUM_SERVER_URL);
      AndroidDriver driver = new AndroidDriver(url, options);
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
   *
   * @param deviceName     name of device
   * @param udid           UDID of device
   * @param automationName name of automation framework
   * @param appPath        path to app
   * @param bundleId       bundle identifier
   * @return IOSDriver instance
   */
  public AndroidDriver createIOSDriver(String deviceName, String udid, String automationName,
                                       String appPath, String bundleId) {
    try {
      log.info("Creating iOS driver for device: {}, UDID: {}", deviceName, udid);

      XCUITestOptions options = new XCUITestOptions();
      options.setPlatformName("iOS");
      options.setDeviceName(deviceName);

      if (udid != null && !udid.isEmpty()) {
        options.setUdid(udid);
      }

      options.setAutomationName(automationName != null ? automationName : "XCUITest");

      if (appPath != null && !appPath.isEmpty()) {
        options.setApp(appPath);
      }

      if (bundleId != null && !bundleId.isEmpty()) {
        options.setBundleId(bundleId);
      }

      options.setAutoAcceptAlerts(true);
      options.setNoReset(false);
      options.setNewCommandTimeout(Duration.ofSeconds(300));

      URL url = new URL(APPIUM_SERVER_URL);
      AndroidDriver driver = new AndroidDriver(new HttpCommandExecutor(url), options);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

      log.info("iOS driver created successfully");
      return driver;

    } catch (Exception e) {
      log.error("Failed to create iOS driver", e);
      throw new RuntimeException("Failed to create iOS driver", e);
    }
  }
}
