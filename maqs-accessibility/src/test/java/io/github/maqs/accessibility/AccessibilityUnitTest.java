/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.accessibility;

import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import io.github.maqs.selenium.BaseSeleniumTest;
import io.github.maqs.selenium.SeleniumConfig;
import io.github.maqs.selenium.factories.UIWaitFactory;
import io.github.maqs.utilities.helper.TestCategories;
import io.github.maqs.utilities.logging.FileLogger;
import io.github.maqs.utilities.logging.MessageType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * The Accessibility Unit test class.
 */
public class AccessibilityUnitTest extends BaseSeleniumTest {

  /**
   * Unit testing accessibility site URL - Login page.
   */
  private static final String testSiteAccessibilityUrl = SeleniumConfig.getWebSiteBase() + "../Training1/LoginPage.html";

  /**
   * Navigates and sets up the login page for scanning.
   */
  @BeforeMethod
  public void setup() {
    this.getWebDriver().navigate().to(testSiteAccessibilityUrl);
    UIWaitFactory.getWaitDriver(getWebDriver()).waitForPageLoad();
  }

  /**
   * Verify we get verbose message back.
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityCheckVerbose() throws IOException {
    String filePath = ((FileLogger)getLogger()).getFilePath();
    AccessibilityUtilities.checkAccessibility(getTestObject(), false);
    String logContent = String.valueOf(Files.readAllLines(Paths.get(filePath)));

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logContent.contains("Found 15 items"), "Expected to find 15 pass matches.");
    softAssert.assertTrue(logContent.contains("Found 67 items"), "Expected to find 67 inapplicable matches.");
    softAssert.assertTrue(logContent.contains("Found 6 items"), "Expected to find 6 violations matches.");
    softAssert.assertTrue(logContent.contains("Found 0 items"), "Expected to find 0 incomplete matches.");
    softAssert.assertAll();
    deleteFile(filePath);
  }

  /**
   * Verify message levels are respected.
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityCheckRespectsMessageLevel() {
    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, "LevTest.txt", MessageType.WARNING);

    try {
      AccessibilityUtilities.checkAccessibility(getTestObject().getWebDriver(), fileLogger, false);

      String logContent = String.valueOf(Files.readAllLines(Paths.get(fileLogger.getFilePath())));

      Assert.assertFalse(logContent.contains("PASSES check for"),
          "Did not expect expected to check for pass matches.");
      Assert.assertFalse(logContent.contains("INAPPLICABLE check for"),
          "Did not expect expected to check for inapplicable matches.");
      Assert.assertFalse(logContent.contains("INCOMPLETE check for"),
          "Did not expected to find any incomplete matches.");
      Assert.assertTrue(logContent.contains("Found 6 items"),
          "Expected to find 6 violations matches.");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      deleteFile(filePath);
    }
  }

  /**
   * Verify inapplicable only check respected.
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityInapplicableCheckRespectsMessageLevel() {
    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityInapplicable(
          getTestObject().getWebDriver(), fileLogger, MessageType.WARNING, false);
      String logContent = String.valueOf(Files.readAllLines(Paths.get(fileLogger.getFilePath())));

      Assert.assertFalse(logContent.contains("PASSES check"), "Did not expect pass check");
      Assert.assertFalse(logContent.contains("INAPPLICABLE check"), "Did not expect inapplicable check");
      Assert.assertFalse(logContent.contains("INCOMPLETE check"), "Did not expect incomplete check");
      Assert.assertTrue(logContent.contains("VIOLATIONS check"), "Did expect violation check");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      deleteFile(filePath);
    }
  }

  /**
   * Verify incomplete only check respected
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityIncompleteCheckRespectsMessageLevel() {
    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityIncomplete(
          getTestObject().getWebDriver(), fileLogger, MessageType.WARNING, false);
      String logContent = String.valueOf(Files.readAllLines(Paths.get(fileLogger.getFilePath())));

      Assert.assertFalse(logContent.contains("PASSES check"), "Did not expect pass check");
      Assert.assertFalse(logContent.contains("INAPPLICABLE check"), "Did not expect inapplicable check");
      Assert.assertFalse(logContent.contains("INCOMPLETE check"), "Did not expect incomplete check");
      Assert.assertTrue(logContent.contains("VIOLATIONS check"), "Did expect violation check");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      deleteFile(filePath);
    }
  }

  /**
   * Verify passes only check respected
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityPassesCheckRespectsMessageLevel() {
    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities
          .checkAccessibilityPasses(getTestObject().getWebDriver(), fileLogger, MessageType.SUCCESS);
      String logContent = String.valueOf(Files.readAllLines(Paths.get(fileLogger.getFilePath())));

      Assert.assertFalse(logContent.contains("PASSES check"), "Did not expect pass check");
      Assert.assertFalse(logContent.contains("INAPPLICABLE check"), "Did not expect inapplicable check");
      Assert.assertFalse(logContent.contains("INCOMPLETE check"), "Did not expect incomplete check");
      Assert.assertTrue(logContent.contains("VIOLATIONS check"), "Did expect violation check");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      deleteFile(filePath);
    }
  }

  /**
   * Verify violation only check respected.
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityViolationsCheckRespectsMessageLevel() {
    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities
          .checkAccessibilityViolations(getTestObject().getWebDriver(), fileLogger, MessageType.ERROR, false);
      String logContent = String.valueOf(Files.readAllLines(Paths.get(fileLogger.getFilePath())));

      Assert.assertFalse(logContent.contains("PASSES check"), "Did not expect pass check");
      Assert.assertFalse(logContent.contains("INAPPLICABLE check"), "Did not expect inapplicable check");
      Assert.assertFalse(logContent.contains("INCOMPLETE check"), "Did not expect incomplete check");
      Assert.assertTrue(logContent.contains("VIOLATIONS check"), "Did expect violation check");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      deleteFile(filePath);
    }
  }

  /**
   * Verify accessibility exception will be thrown.
   */
    @Test(groups = TestCategories.ACCESSIBILITY, expectedExceptions = RuntimeException.class)
  public void testAccessibilityCheckThrows() {
      AccessibilityUtilities.checkAccessibility(getTestObject(), true);
  }

  /**
   * Verify accessibility does not throw when no exception are found
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityCheckNoThrowOnNoResults() {
    // There should be 0 incomplete items found
    AccessibilityUtilities.checkAccessibilityIncomplete(getTestObject().getWebDriver(),
        getTestObject().getLogger(), MessageType.WARNING, true);
  }

  /**
   * Verify we can get readable results directly
   */
  @Test(groups = TestCategories.ACCESSIBILITY)
  public void testAccessibilityReadableResults() {
    AxeReporter.getReadableAxeResults("TEST", getWebDriver(),
        new AxeBuilder().analyze(getWebDriver()).getViolations());
    String messages = AxeReporter.getAxeResultString();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(messages.contains("TEST check for"), "Expected header.");
    softAssert.assertTrue(messages.contains("Found 6 items"), "Expected to find 6 violations matches.");
    softAssert.assertAll();
  }

  private void deleteFile(String filePath) {
    File file = new File(filePath);
    Assert.assertTrue(file.delete());
    Assert.assertFalse(file.exists(), "File Still exists");
  }
}
