/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.deque.html.axecore.results.AxeRuntimeException;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.deque.html.axecore.selenium.ResultType;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.function.Supplier;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utilities class for Accessibility Functionality.
 */
public class AccessibilityUtilities {
  /**
   * private constructor.
   */
  private AccessibilityUtilities() {
  }

  /**
   * Run axe accessibility and log the results.
   * @param testObject The test object which contains the web driver and logger you wish to use
   * @param throwOnViolation Should violations cause and exception to be thrown
   */
  public static void checkAccessibility(SeleniumTestObject testObject, boolean throwOnViolation) {
    checkAccessibility(testObject.getWebDriver(), testObject.getLogger(), throwOnViolation);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param throwOnViolation Should violations cause and exception to be thrown
   */
  public static void checkAccessibility(WebDriver webDriver, Logger logger, boolean throwOnViolation) {
    MessageType type = logger.getLoggingLevel();

    // Look at passed
    if (type.getValue() >= MessageType.SUCCESS.getValue()) {
      checkAccessibilityPasses(webDriver, logger, MessageType.SUCCESS);
    }

    // Look at incomplete
    if (type.getValue() >= MessageType.INFORMATION.getValue()) {
      checkAccessibilityIncomplete(webDriver, logger, MessageType.INFORMATION, throwOnViolation);
    }

    // Look at inapplicable
    if (type.getValue() >= MessageType.VERBOSE.getValue()) {
      checkAccessibilityInapplicable(webDriver, logger, MessageType.VERBOSE, throwOnViolation);
    }

    // Look at violations
    MessageType messageType = throwOnViolation ? MessageType.ERROR : MessageType.WARNING;
    checkAccessibilityViolations(webDriver, logger, messageType, throwOnViolation);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param checkType What kind of check is being run
   * @param getResults Function for getting Axe results
   * @param loggingLevel What level should logging the check take,
   *                     this gets used if the check doesn't throw an exception
   * @param throwOnResults Throw error if any results are found
   */
  public static void checkAccessibility(WebDriver webDriver, Logger logger, String checkType,
      Supplier<List<Rule>> getResults, MessageType loggingLevel, boolean throwOnResults) {
    logger.logMessage(MessageType.INFORMATION, "Running accessibility check");

    if (AxeReporter.getReadableAxeResults(checkType, webDriver, getResults.get()) && throwOnResults) {
      throw new AxeRuntimeException(AxeReporter.getAxeResultString());
    } else {
      logger.logMessage(loggingLevel, AxeReporter.getAxeResultString());
    }
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param loggingLevel What level should logging the check take,
   *                     this gets used if the check doesn't throw an exception
   */
  public static void checkAccessibilityPasses(WebDriver webDriver, Logger logger, MessageType loggingLevel) {
    AxeBuilder axeBuilder = new AxeBuilder();
    checkAccessibility(webDriver, logger, ResultType.Passes.getKey(),
        () -> axeBuilder.analyze(webDriver).getPasses(), loggingLevel, false);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param loggingLevel What level should logging the check take,
   *                     this gets used if the check doesn't throw an exception
   * @param throwOnInapplicable Should inapplicable cause an exception to be thrown
   */
  public static void checkAccessibilityInapplicable(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnInapplicable) {
    AxeBuilder axeBuilder = new AxeBuilder();
    checkAccessibility(webDriver, logger, ResultType.Inapplicable.getKey(),
        () -> axeBuilder.analyze(webDriver).getInapplicable(), loggingLevel, throwOnInapplicable);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param loggingLevel What level should logging the check take,
   *                     this gets used if the check doesn't throw an exception
   * @param throwOnIncomplete Should incomplete cause an exception to be thrown
   */
  public static void checkAccessibilityIncomplete(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnIncomplete) {
    AxeBuilder axeBuilder = new AxeBuilder();
    checkAccessibility(webDriver, logger, ResultType.Incomplete.getKey(),
        () -> axeBuilder.analyze(webDriver).getIncomplete(), loggingLevel, throwOnIncomplete);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param loggingLevel What level should logging the check take,
   *                     this gets used if the check doesn't throw an exception
   * @param throwOnViolation Should violations cause an exception to be thrown
   */
  public static void checkAccessibilityViolations(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnViolation) {
    AxeBuilder axeBuilder = new AxeBuilder();
    checkAccessibility(webDriver, logger, ResultType.Violations.getKey(),
        () -> axeBuilder.analyze(webDriver).getViolations(), loggingLevel, throwOnViolation);
  }

  /**
   * Create a HTML accessibility report for an entire web page.
   * @param testObject The TestObject to associate the report with
   * @param throwOnViolation Should violations cause an exception to be thrown
   */
  public static void createAccessibilityHtmlReport(SeleniumTestObject testObject,
      boolean throwOnViolation) throws IOException, ParseException {
    AxeBuilder axeBuilder = new AxeBuilder();
    createAccessibilityHtmlReport(testObject,
        () -> axeBuilder.analyze(testObject.getWebDriver()), throwOnViolation);
  }

  /**
   * Create a HTML accessibility report for a specific web element and all of it's children.
   * @param testObject The TestObject to associate the report with
   * @param element The WebElement you want to use as the root for your accessibility scan
   * @param throwOnViolation Should violations cause an exception to be thrown
   */
  public static void createAccessibilityHtmlReport(SeleniumTestObject testObject,
      WebElement element, boolean throwOnViolation) throws IOException, ParseException {

    AxeBuilder axeBuilder = new AxeBuilder();
    createAccessibilityHtmlReport(testObject, () -> axeBuilder.analyze(testObject.getWebDriver(),
        element), throwOnViolation);
  }

  /**
   * Create a HTML accessibility report.
   * @param testObject The TestObject to associate the report with
   * @param getResults Function for getting the accessibility scan results
   * @param throwOnViolation Should violations cause an exception to be thrown
   */
  public static void createAccessibilityHtmlReport(SeleniumTestObject testObject,
      Supplier<Results> getResults, boolean throwOnViolation) throws IOException, ParseException {

    // Check to see if the logger is not verbose and not already suspended
    boolean restoreLogging = testObject.getLogger().getLoggingLevel() != MessageType.VERBOSE
        && testObject.getLogger().getLoggingLevel() != MessageType.SUSPENDED;

    String reportPath = getAccessibilityReportPath(testObject);
    testObject.getLogger().logMessage(MessageType.INFORMATION, "Running accessibility check");

    try {
      // Suspend logging if we are not verbose or already suspended
      if (restoreLogging) {
        testObject.getLogger().suspendLogging();
      }

      HTMLReport.createAxeHtmlReport(testObject, reportPath);
    } finally {
      // Restore logging if we suspended it
      if (restoreLogging) {
        testObject.getLogger().continueLogging();
      }
    }

    // Add the report
    testObject.addAssociatedFile(reportPath);
    testObject.getLogger().logMessage(MessageType.INFORMATION,
        "Ran accessibility check and created HTML report: " + reportPath + " ");

    // Throw exception if we found violations and we want that to cause an error
    if (throwOnViolation && getResults.get().getViolations().isEmpty()) {
      throw new AxeRuntimeException("Accessibility violations, see: " + reportPath + " for more details.");
    }

    // Throw exception if the accessibility check had any errors
    if (getResults.get().isErrored()) {
      throw new AxeRuntimeException("Accessibility check failure, see: " + reportPath + " for more details.");
    }
  }

  /**
   * Get a unique file name that we can user for the accessibility HTML report.
   * @param testObject The TestObject to associate the report with
   * @return A unique HTML file name, includes full path
   */
  private static String getAccessibilityReportPath(SeleniumTestObject testObject) {
    String reportBaseName = testObject.getLogger() instanceof FileLogger ? FilenameUtils
        .removeExtension((((FileLogger)testObject.getLogger()).getFilePath())) + "_Axe" : "AxeReport";

    File reportFile = new File(String.valueOf(Paths.get(reportBaseName + ".html")));
    int reportNumber = 0;

    while (reportFile.exists()) {
      reportFile = new File(String.valueOf(Paths.get(reportBaseName + reportNumber++ + ".html")));
    }

    return reportFile.getPath();
  }
}