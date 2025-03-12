package com.example.base;

import com.example.constants.TimeoutConstants;
import com.example.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class BasePage {
  protected AppiumDriver driver;
  protected WebDriverWait wait;
  protected WaitUtils waitUtils;

  public BasePage(AppiumDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(TimeoutConstants.DEFAULT_WAIT_TIMEOUT));
    this.waitUtils = new WaitUtils(driver);
    PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(TimeoutConstants.DEFAULT_IMPLICIT_WAIT)), this);
  }

  /**
   * Click on element with explicit wait
   * @param element WebElement to click
   */
  protected void click(WebElement element) {
    waitUtils.waitForElementToBeClickable(element);
    log.info("Clicking on element: {}", element);
    element.click();
  }

  /**
   * Type text in input field
   * @param element WebElement to type in
   * @param text Text to type
   */
  protected void type(WebElement element, String text) {
    waitUtils.waitForElementToBeVisible(element);
    log.info("Typing text '{}' into element: {}", text, element);
    element.clear();
    element.sendKeys(text);
  }

  /**
   * Get text from element
   * @param element WebElement to get text from
   * @return Text of element
   */
  protected String getText(WebElement element) {
    waitUtils.waitForElementToBeVisible(element);
    log.info("Getting text from element: {}", element);
    return element.getText();
  }

  /**
   * Check if element is displayed
   * @param element WebElement to check
   * @return true if element is displayed, false otherwise
   */
  protected boolean isElementDisplayed(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (Exception e) {
      log.warn("Element is not displayed: {}", element);
      return false;
    }
  }

  /**
   * Wait for element to appear with locator
   * @param locator By locator
   * @return WebElement
   */
  protected WebElement waitForElement(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Swipe from one point to another
   * @param startX start X coordinate
   * @param startY start Y coordinate
   * @param endX end X coordinate
   * @param endY end Y coordinate
   * @param durationMs duration in milliseconds
   */
  protected void swipe(int startX, int startY, int endX, int endY, int durationMs) {
    waitUtils.swipe(startX, startY, endX, endY, durationMs);
  }

  /**
   * Tap by coordinates
   * @param x X coordinate
   * @param y Y coordinate
   */
  protected void tapByCoordinates(int x, int y) {
    waitUtils.tapByCoordinates(x, y);
  }
}
