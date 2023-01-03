/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.selenium;

import io.github.maqs.selenium.factories.UIWaitFactory;
import io.github.maqs.selenium.pageModel.AutomationPageModel;
import io.github.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The Action Builder unit test class.
 */
public class ActionBuilderUnitTest extends BaseSeleniumTest {

  /**
   * The automation page model for the unit tests.
   */
  private AutomationPageModel automationPageModel;

  /**
   * Navigates to the specified url test page.
   */
  @BeforeMethod
  private void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
  }

  /**
   * Test hover over functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void hoverOverTest() {
    ActionBuilder.hoverOver(this.getWebDriver(), automationPageModel.automationDropDown);
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.iFrameDropDownButton).click();
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForExactText(automationPageModel.iFramePageTitle, "Index");
  }

  /**
   * Test press modifier key functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void pressModifierKeyTest() {
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption1).click();
    ActionBuilder.pressModifierKey(this.getWebDriver(), Keys.CONTROL);
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption2).click();

    Assert.assertTrue(UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption1).isSelected());
    Assert.assertTrue(UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption2).isSelected());
  }

  /**
   * Test move slider functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void moveSliderTest() {
    ActionBuilder.slideElement(this.getWebDriver(), automationPageModel.slider, 50);
    Assert.assertEquals(this.getWebDriver().findElement(
            automationPageModel.sliderLabelNumber).getAttribute("value"), "4");
  }

  /**
   * Test move right click to trigger context menu functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void rightClickToTriggerContextMenu() {
    ActionBuilder.rightClick(this.getWebDriver(), automationPageModel.rightClickableElementWithContextMenu);
    Assert.assertTrue(this.getWebDriver().findElement(automationPageModel.rightClickContextSaveText).isDisplayed());
  }
}
