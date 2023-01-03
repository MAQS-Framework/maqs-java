/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.utilities.logger;

import io.github.maqs.utilities.helper.Config;
import io.github.maqs.utilities.helper.TestCategories;
import io.github.maqs.utilities.helper.exceptions.MaqsLoggingConfigException;
import io.github.maqs.utilities.logging.ConsoleLogger;
import io.github.maqs.utilities.logging.FileLogger;
import io.github.maqs.utilities.logging.HtmlFileLogger;
import io.github.maqs.utilities.logging.ILogger;
import io.github.maqs.utilities.logging.LoggerFactory;
import io.github.maqs.utilities.logging.MessageType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Logger Factory unit test class.
 */
@Test(singleThreaded = true)
public class LoggerFactoryUnitTest {

  /**
   * Test getting the logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetLogger() {
    Assert.assertNotNull(LoggerFactory.getLogger("Test Name"));

    HashMap<String, String> newValueMap = new HashMap<>();
    newValueMap.put("Log", "NO");
    newValueMap.put("LogType", "TXT");
    Config.addGeneralTestSettingValues(newValueMap, true);
    Assert.assertNotNull(LoggerFactory.getLogger("Test Name"));
  }

  /**
   * Test getting the console logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetConsoleLogger() {
    Assert.assertNotNull(LoggerFactory.getConsoleLogger());
    ILogger logger = LoggerFactory.getLogger("logName", "CONSOLE", MessageType.WARNING);
    Assert.assertTrue(logger instanceof ConsoleLogger);
  }

  /**
   * Tests getting the file logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetFileLogger() {
    List<String> logType = new ArrayList<>();
    logType.add("TXT");
    logType.add("TEXT");

    for (String log : logType) {
      ILogger logger = LoggerFactory.getLogger("logName", log, MessageType.ACTION);
      Assert.assertTrue(logger instanceof FileLogger);
    }
  }

  /**
   * Test getting the HTML file logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetHtmlFileLogger() {
    List<String> logType = new ArrayList<>();
    logType.add("HTML");
    logType.add("HTM");

    for (String log : logType) {
      ILogger logger = LoggerFactory.getLogger("logName", log, MessageType.ACTION);
      Assert.assertTrue(logger instanceof HtmlFileLogger);
    }
  }

  /**
   * Test throwing an exception with an unsupported log type.
   */
  @Test(groups = TestCategories.UTILITIES, expectedExceptions = MaqsLoggingConfigException.class)
  public void testGetInvalidLogger() {
    LoggerFactory.getLogger("logName", "TEST", MessageType.ACTION);
  }
}
