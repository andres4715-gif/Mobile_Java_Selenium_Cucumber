package com.example.pages;

import com.example.base.BasePage;
import com.example.locators.HomePageLocators;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

@Log4j2
public class HomePage extends BasePage {

  private final HomePageLocators elements;

  public HomePage(AppiumDriver driver) {
    super(driver);
    this.elements = new HomePageLocators();
    PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), elements);
    log.info("✅ Home Page initialized");
  }

  /**
   * Click on userName element
   *
   * @return HomePage instance
   */
//  public UserDetails clickFirstUser() {
  public HomePage clickFirstUser() {
    log.info("Clicking on the userName element");
    click(elements.username);
//    return new UserDetails(driver);
    return this;
  }

  public boolean verifyElementInTheHomePage() {
    log.info("Home page is displayed");
    return elements.username.isDisplayed();
  }
}
