package com.magenic.jmaqs.appium.pagemodels;

import com.magenic.jmaqs.appium.baseAppiumTest.AppiumTestObject;

/**
 * Base Page Model Class.
 */
public abstract class BasePageModel {

  /**
   * The Appium test object.
   */
  protected AppiumTestObject appiumTestObject;

  /**
   * Instantiates a new Base page model.
   *
   * @param appiumTestObject the appium test object
   */
  public BasePageModel(AppiumTestObject appiumTestObject) {
    this.appiumTestObject = appiumTestObject;
  }
}
