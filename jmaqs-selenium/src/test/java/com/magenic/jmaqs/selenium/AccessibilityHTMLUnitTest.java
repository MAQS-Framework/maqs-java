/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccessibilityHTMLUnitTest extends BaseSeleniumTest{
  /**
   * Axe JSON with an error.
   */
  // TODO: use after HTML Report functionality has been accepted by DeQue
  private final String axeResultWithError = "{\"error\":\"AutomationError\",\"results\":{\"testEngine\": { \"name\":\"axe-core\",\"version\":\"3.4.1\"}, \"testRunner\": { \"name\":\"axe\"}, \"testEnvironment\": { \"userAgent\":\"AutoAgent\",\"windowWidth\": 1200, \"windowHeight\": 646, \"orientationAngle\": 0, \"orientationType\":\"landscape-primary\"},\"timestamp\":\"2020-04-14T01:33:59.139Z\",\"url\":\"url\",\"toolOptions\":{\"reporter\":\"v1\"},\"violations\":[],\"passes\":[],\"incomplete\":[],\"inapplicable\": []}}";

  /**
   * Unit testing site URL - Login page.
   */
  private final static String TestSiteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Unit testing site URL - Automation page.
   */
  private final static String TestSiteAutomationUrl = TestSiteUrl + "Automation/";

  /**
   * Verify we can create and associate an accessibility HTML report.
   * @throws IOException if an exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlReport() throws IOException, ParseException {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(), false);

    String file = Arrays.stream(this.getTestObject().getArrayOfAssociatedFiles())
        .filter(x -> x.contains(".html")).findFirst().toString();
    Assert.assertTrue(file.length() > 0, "Accessibility report is empty");

    File filePath = new File(file);
    Assert.assertTrue(filePath.exists(), "File does not exist");
    Assert.assertTrue(filePath.delete(), "File was not deleted");
  }

  /**
   * Verify we can create and associate multiple accessibility HTML reports.
   * @throws IOException if an exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityMultipleHtmlReports() throws IOException, ParseException {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    // Create 3 reports
    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(), false);
    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(), false);
    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(), false);

    long count = Arrays.stream(this.getTestObject().getArrayOfAssociatedFiles())
        .filter(x -> x.contains(".html")).count();
    Assert.assertEquals(count, 3,
        "Expected 3 accessibility reports but see " + count + " instead");
    String[] reports = this.getTestObject().getArrayOfAssociatedFiles();

    for (String file : reports) {
      if (file.contains(".html")) {
        File filePath = new File(file);
        Assert.assertTrue(filePath.delete(), "File was not deleted");
      }
    }
  }

  /**
   * Verify we throw an exception if the scan has an error.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = Exception.class)
  public void AccessibilityHtmlReportWithError() throws IOException, ParseException {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    ObjectMapper objectMapper = new ObjectMapper();
    Results results = objectMapper.convertValue(axeResultWithError, Results.class);
    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(),
        () -> results, false);

    String file = Arrays.stream(this.getTestObject().getArrayOfAssociatedFiles())
        .filter(x -> x.contains(".html")).findFirst().toString();
    Assert.assertTrue(file.length() > 0, "Accessibility report is empty");

    File filePath = new File(file);
    Assert.assertTrue(filePath.delete(), "File was not deleted");
  }

  /**
   * Verify we throw an exception if the scan has an error and are using lazy elements.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = Exception.class)
  // [ExpectedException(typeof(ApplicationException))]
  public void AccessibilityHtmlReportWithErrorFromLazyElement() throws IOException, ParseException {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    LazyWebElement foodTable = new LazyWebElement(this.getTestObject(),
        By.id("FoodTable"), "Food Table");
    AxeBuilder builder = new AxeBuilder();
    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(),
        () -> builder.analyze(this.getWebDriver(), foodTable.getCachedElement()),false);

    String file = Arrays.stream(this.getTestObject().getArrayOfAssociatedFiles())
        .filter(x -> x.contains(".html")).findFirst().toString();

    Assert.assertTrue(file.length() > 0, "Accessibility report is empty");

    File filePath = new File(file);
    Assert.assertTrue(filePath.delete(), "File was not deleted");
  }

  /**
   * Verify we throw an exception if there are violations and we choose the throw exception option.
   * @throws IOException if an exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = Exception.class)
  //[ExpectedException(typeof(ApplicationException))]
  public void AccessibilityHtmlReportWithViolation() throws IOException, ParseException {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(), true);
    String file = Arrays.stream(this.getTestObject().getArrayOfAssociatedFiles())
        .filter(x -> x.contains(".html")).findFirst().toString();
    File filePath = new File(file);
    Assert.assertTrue(filePath.exists(), "File was not created");
    Assert.assertTrue(filePath.delete(), "File was not deleted");
  }

  /**
   * Verify we can create an accessibility HTML report off a lazy element.
   * @throws IOException if exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlReportWithLazyElement() throws IOException, ParseException {
    getWebDriver().get(TestSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    LazyWebElement foodTable = new LazyWebElement(this.getTestObject(),
        By.id("FoodTable"), "Food Table");
    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(),
        foodTable.getCachedElement(), false);

    String file = Arrays.stream(this.getTestObject().getArrayOfAssociatedFiles())
        .filter(x -> x.contains(".html")).findFirst().toString();
    Assert.assertFalse(file.isEmpty(), "Accessibility report is empty");

    File filePath = new File(file);
    Assert.assertTrue(filePath.delete(), "File was not deleted");
  }

  /**
   *  Verify we can create an accessibility HTML report off a normal web element.
   * @throws IOException if exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlReportWithElement() throws IOException, ParseException {
    getWebDriver().navigate().to(TestSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(),
        this.getWebDriver().findElement(By.id("FoodTable")), false);

    String file = Arrays.stream(this.getTestObject().getArrayOfAssociatedFiles())
        .filter(x -> x.contains(".html")).findFirst().toString();
    // Assert.assertTrue(new FileInfo(file).Length > 0, "Accessibility report is empty");
    Assert.assertFalse(file.isEmpty(), "Accessibility report is empty");

    File filePath = new File(file);
    Assert.assertTrue(filePath.exists(), "File does not exist");
    Assert.assertTrue(filePath.delete(), "File was not deleted");
  }

  /**
   * Verify we suppress the JS logging associated with running Axe.
   * @throws IOException if an exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlLogSuppression() throws IOException, ParseException {
    // Make sure we are not using verbose logging
    this.getLogger().setLoggingLevel(MessageType.INFORMATION);

    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    AccessibilityUtilities.createAccessibilityHtmlReport(this.getTestObject(), false);

    // The script executed message should be suppressed when we run the accessibility check
    FileInputStream fis = new FileInputStream(((FileLogger)this.getLogger()).getFilePath());
    String file = IOUtils.toString(fis, StandardCharsets.UTF_8);
    Assert.assertFalse(file.contains("Script executed"), "Logging was not suppressed as expected");

    File filePath = new File(file);
    Assert.assertTrue(filePath.delete(), "File was not deleted");
  }
}
