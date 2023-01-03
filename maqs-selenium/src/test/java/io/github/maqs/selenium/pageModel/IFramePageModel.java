/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.selenium.pageModel;

import io.github.maqs.selenium.ISeleniumTestObject;
import io.github.maqs.selenium.SeleniumConfig;
import org.openqa.selenium.By;

/**
 * The IFrame page model.
 */
public class IFramePageModel extends HeaderPageModel {

  /**
   * Unit testing site URL - IFrame page.
   */
  public final String testSiteIFrameUrl = SeleniumConfig.getWebSiteBase() + "iFrame.html";

  /**
   * The Iframe locator.
   */
  public By iframeLocator = By.cssSelector("#frame");

  /**
   * Instantiates a new IFrame page model.
   *
   * @param testObject the test object
   */
  public IFramePageModel(ISeleniumTestObject testObject) {
    super(testObject);
  }

  /**
   * Opens the page to the specified url.
   */
  public void open() {
    open(testSiteIFrameUrl);
  }
}