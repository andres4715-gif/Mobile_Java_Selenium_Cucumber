package com.example.utils;

import com.example.constants.TimeoutConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class WaitUtils {
  private final AppiumDriver driver;
  private final WebDriverWait wait;

  public WaitUtils(AppiumDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(TimeoutConstants.DEFAULT_WAIT_TIMEOUT));
  }

  /**
   * Wait for element to be clickable
   * @param element element to wait for
   * @return WebElement
   */
  public WebElement waitForElementToBeClickable(WebElement element) {
    log.debug("Waiting for element to be clickable: {}", element);
    return wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  /**
   * Wait for element to be visible
   * @param element element to wait for
   * @return WebElement
   */
  public WebElement waitForElementToBeVisible(WebElement element) {
    log.debug("Waiting for element to be visible: {}", element);
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  /**
   * Wait for element to be visible with custom timeout
   * @param element element to wait for
   * @param timeoutInSeconds timeout in seconds
   * @return WebElement
   */
  public WebElement waitForElementToBeVisible(WebElement element, long timeoutInSeconds) {
    log.debug("Waiting for element to be visible with timeout {}: {}", timeoutInSeconds, element);
    WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    return customWait.until(ExpectedConditions.visibilityOf(element));
  }

  /**
   * Wait for element to be invisible
   * @param element element to wait for
   * @return boolean
   */
  public boolean waitForElementToBeInvisible(WebElement element) {
    log.debug("Waiting for element to be invisible: {}", element);
    return wait.until(ExpectedConditions.invisibilityOf(element));
  }

  /**
   * Wait for element to be present
   * @param locator locator to find element
   * @return WebElement
   */
  public WebElement waitForElementToBePresent(By locator) {
    log.debug("Waiting for element to be present: {}", locator);
    return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  /**
   * Swipe from one point to another
   * @param startX start X coordinate
   * @param startY start Y coordinate
   * @param endX end X coordinate
   * @param endY end Y coordinate
   * @param durationMs duration in milliseconds
   */
  @SuppressWarnings("rawtypes")
  public void swipe(int startX, int startY, int endX, int endY, int durationMs) {
    log.debug("Swiping from ({},{}) to ({},{}) with duration {}ms", startX, startY, endX, endY, durationMs);
    new TouchAction((PerformsTouchActions) driver)
      .press(PointOption.point(startX, startY))
      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationMs)))
      .moveTo(PointOption.point(endX, endY))
      .release()
      .perform();
  }

  /**
   * Tap by coordinates
   * @param x X coordinate
   * @param y Y coordinate
   */
  @SuppressWarnings("rawtypes")
  public void tapByCoordinates(int x, int y) {
    log.debug("Tapping at coordinates ({},{})", x, y);
    new TouchAction((PerformsTouchActions) driver)
      .tap(PointOption.point(x, y))
      .perform();
  }

  /**
   * Wait for specific duration
   * @param milliseconds duration to wait in milliseconds
   */
  public void waitFor(long milliseconds) {
    log.debug("Waiting for {}ms", milliseconds);
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.error("Thread interrupted during wait", e);
    }
  }
}
