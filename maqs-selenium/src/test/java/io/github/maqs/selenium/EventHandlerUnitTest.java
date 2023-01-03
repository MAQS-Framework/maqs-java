/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.selenium;

import io.github.maqs.base.exceptions.MAQSRuntimeException;
import io.github.maqs.selenium.factories.UIWaitFactory;
import io.github.maqs.selenium.pageModel.AutomationPageModel;
import io.github.maqs.utilities.helper.TestCategories;
import io.github.maqs.utilities.logging.FileLogger;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WrapsDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Unit tests for EventHandler class.
 */
public class EventHandlerUnitTest extends BaseSeleniumTest {

  /**
   * The Automation Page Model.
   */
  private AutomationPageModel automationPageModel;

  /**
   * Navigate to test page url and wait for page to load.
   */
  @BeforeMethod
  private void setUp() {
    // Navigate to the Automation site and set up the event handler
    automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(getWebDriver()).waitForPageLoad();
  }

  /**
   * Test that checks if the correct messages are logged when clicking an element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerClickElement() {
    // Use the Event Firing Web Driver to click an element, then get the log text
    this.getWebDriver().findElement(automationPageModel.checkbox).click();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before clicking element"),
        "Expected message to be logged before clicking element.");
    softAssert.assertTrue(logText.contains("Element clicked"), "Expected message to be logged after clicking element.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when changing the value
   * of an element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerChangeValueOf() {
    // Use the Event Firing Web Driver to change the value of an element, then get the log text
    this.getWebDriver().findElement(automationPageModel.firstNameTextBox).clear();
    this.getWebDriver().findElement(automationPageModel.firstNameTextBox).sendKeys("Change Value");
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Element value before clear"),
        "Expected message to be logged before changing the value of element.");
    softAssert.assertTrue(logText.contains("Element value changed to"),
        "Expected message to be logged after changing the value of element.");
    softAssert.assertTrue(logText.contains("Element value before change"),
        "Expected message to be logged before changing the value of element.");
    softAssert.assertTrue(logText.contains("Element value changed to"),
        "Expected message to be logged after changing the value of element.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when finding an element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerFindBy() {
    // Use the Event Firing Web Driver to find an element, then get the log text
    this.getWebDriver().findElement(automationPageModel.computerPartsList);
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before finding element By"),
        "Expected message to be logged before clicking element.");
    softAssert.assertTrue(logText.contains("Found element By"),
        "Expected message to be logged after clicking element.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when navigating back to
   * the previous page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerNavigateBack() {
    // Use the Event Firing Web Driver to navigate back to a page, then get the log text
    this.getWebDriver().findElement(automationPageModel.homeButton).click();
    this.getWebDriver().navigate().back();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before navigating back to previous page"),
        "Expected message to be logged before navigating back to the previous page.");
    softAssert.assertTrue(logText.contains("Navigated back to previous page"),
        "Expected message to be logged after navigating back to the previous page.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when navigating forward
   * to a page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerNavigateForward() {
    // Use the Event Firing Web Driver to navigate forward to a page, then get the log text
    this.getWebDriver().findElement(automationPageModel.homeButton).click();
    this.getWebDriver().navigate().back();
    this.getWebDriver().navigate().forward();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before navigating forward to next page"),
        "Expected message to be logged before navigating forward to a page.");
    softAssert.assertTrue(logText.contains("Navigated forward to next page"),
        "Expected message to be logged after navigating forward to a page.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when refreshing a page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerRefresh() {
    // Use the Event Firing Web Driver to refresh the page, then get the log text
    this.getWebDriver().navigate().refresh();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before Refreshing the page"),
        "Expected message to be logged before refreshing a page.");
    softAssert.assertTrue(logText.contains("Page refreshed"), "Expected message to be logged after refreshing a page.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when navigating to a
   * page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerNavigateTo() {
    // Use the Event Firing Web Driver to navigate to a page, then get the log text
//    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before navigating to page"),
        "Expected message to be logged before navigating to a page.");
    softAssert.assertTrue(logText.contains("After navigating to page"),
        "Expected message to be logged after navigating to a page.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when running a script.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerScript() {
    // Use the Event Firing Web Driver to execute a script, then get the log text
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.getWebDriver();
    javascriptExecutor.executeScript("document.querySelector(\"#homeButton > a\");");
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before executing script"),
        "Expected message to be logged before running a script.");
    softAssert.assertTrue(logText.contains("Script executed"), "Expected message to be logged after running a script.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when switching windows.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerSwitchWindow() {
    // Use the Event Firing Web Driver to open a new tab, then get the log text
    ((JavascriptExecutor) this.getWebDriver()).executeScript("window.open()");

    SeleniumUtilities.switchToWindow(this.getTestObject(), "");

    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before executing script: window.open()"),
        "Expected message to be logged before executing the script.");
    softAssert.assertTrue(logText.contains("Script executed: window.open()"),
        "Expected message to be logged after executing the script.");
    softAssert.assertTrue(logText.contains("Before switching to window"),
        "Expected message to be logged before switching windows.");
    softAssert.assertTrue(logText.contains("After switching to window"),
        "Expected message to be logged after switching windows.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when switching windows.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = MAQSRuntimeException.class)
  public void eventHandlerSwitchInvalidWindow() {
    // Use the Event Firing Web Driver to open a new tab, then get the log text
    ((JavascriptExecutor) this.getWebDriver()).executeScript("window.open()");
    SeleniumUtilities.switchToWindow(this.getTestObject(), "TestWindow");
  }

  /**
   * Test that checks if the correct messages are logged when accepting an alert.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerAcceptAlert() {
    // Use the Event Firing Web Driver to accept an alert, then get the log text
    UIWait waitDriver = UIWaitFactory.getWaitDriver(((WrapsDriver) this.getWebDriver()).getWrappedDriver());
    waitDriver.waitForClickableElement(automationPageModel.alert).click();
    Alert alert = this.getWebDriver().switchTo().alert();
    alert.accept();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before accepting the alert"),
        "Expected message to be logged before accepting an alert.");
    softAssert.assertTrue(logText.contains("Alert accepted"),
        "Expected message to be logged after accepting an alert.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when dismissing an alert.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerAcceptDismiss() {
    // Use the Event Firing Web Driver to dismiss an alert, then get the log text
    UIWait waitDriver = UIWaitFactory.getWaitDriver(((WrapsDriver) this.getWebDriver()).getWrappedDriver());
    waitDriver.waitForClickableElement(automationPageModel.alertWithConfirm).click();
    Alert alert = this.getWebDriver().switchTo().alert();
    alert.dismiss();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before dismissing the Alert"),
        "Expected message to be logged before dismissing an alert.");
    softAssert
        .assertTrue(logText.contains("After dismissing the Alert"), "Expected message to be logged after dismissing an alert.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when getting the text
   * from an element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerGetText() {
    // Use the Event Firing Web Driver to get the text from an element, then get the log text
    this.getWebDriver().findElement(automationPageModel.errorLinkBy).getText();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before getting text from element"),
        "Expected message to be logged before getting text from an element.");
    softAssert.assertTrue(logText.contains("Got element text"),
        "Expected message to be logged after getting text from an element.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when taking a screenshot.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerScreenshot() {
    SeleniumUtilities.captureScreenshot(this.getWebDriver(), this.getTestObject());

    // Use the Event Firing Web Driver to take a screenshot, then get the log text
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());
    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before screenshot capture"),
        "Expected message to be logged before taking a screenshot.");
    softAssert.assertTrue(logText.contains("After screenshot capture"),
        "Expected message to be logged after taking a screenshot.");
    softAssert.assertAll();
  }

  /**
   * Read a file and return it as a string.
   *
   * @param filePath The file path to read
   * @return The contents of the file
   */

  private String readTextFile(String filePath) {
    String text = "";
    try {
      text = new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return text;
  }
}