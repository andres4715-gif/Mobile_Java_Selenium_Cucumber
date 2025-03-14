package com.example.stepdefinitions;

import com.example.pages.HomePage;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseSteps {

  protected final AppiumDriver driver;
  protected HomePage homePage;

  public BaseSteps() {
    // Get the driver in the test context
    this.driver = (AppiumDriver) Hooks.getFromTestContext();
    this.homePage = new HomePage(driver);
  }
}