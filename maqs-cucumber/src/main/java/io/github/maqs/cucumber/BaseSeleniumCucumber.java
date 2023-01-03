/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber;

import io.github.maqs.base.BaseTest;
import io.github.maqs.selenium.BaseSeleniumTest;

/**
 * The base Selenium cucumber object.
 */
public class BaseSeleniumCucumber extends BaseCucumberTestNG {

  /**
   * Create a test object.
   *
   * @return A Selenium base test object
   */
  @Override
  public BaseTest createSpecificBaseTest() {
    return new BaseSeleniumTest();
  }
}



