package com.example.locators;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

/**
 * Class to store all WebElements for the Home page
 */
public class HomePageLocators {
  @AndroidFindBy(id = "es.jaimesuarez.rindustest:id/tv_user_name")
  @iOSXCUITFindBy(accessibility = "username_input")
  public WebElement username;
}
