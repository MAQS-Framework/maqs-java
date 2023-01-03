/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber.steps;

import io.github.maqs.cucumber.ScenarioContext;
import io.github.maqs.selenium.BaseSeleniumTest;
import io.github.maqs.selenium.SeleniumTestObject;
import org.openqa.selenium.WebDriver;

/**
 * Base Selenium cucumber step.
 */
public class BaseSeleniumStep extends BaseGenericStep {

  /**
   * Get the web driver.
   *
   * @return The web driver
   */
  public WebDriver getDriver() {
    return getTestObject().getWebDriver();
  }

  /**
   * Get the Selenium test object.
   *
   * @return Selenium test object
   */
  @Override
  public SeleniumTestObject getTestObject() {
    return (SeleniumTestObject) ScenarioContext.get(
        ScenarioContext.MAQS_HOLDER, BaseSeleniumTest.class).getTestObject();
  }
}



