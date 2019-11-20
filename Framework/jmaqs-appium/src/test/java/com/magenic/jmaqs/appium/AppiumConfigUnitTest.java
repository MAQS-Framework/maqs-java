/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.appium;

import com.magenic.jmaqs.appium.constants.PlatformType;
import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.ConfigSection;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * The type Appium config unit test.
 */
public class AppiumConfigUnitTest {

  /**
   * The Username.
   */
  private String username = "username";
  /**
   * The Browser name.
   */
  private String browserName = "browserName";
  /**
   * The Access key.
   */
  private String accessKey = "accessKey";
  /**
   * The Device orientation.
   */
  private String deviceOrientation = "deviceOrientation";

  /**
   * Test get os version.
   *
   * @throws Exception the exception
   */
  @Test(groups = TestCategories.Appium)
  public void testGetOSVersion() throws Exception {
    String osVersion = AppiumConfig.getPlatformVersion();
    Assert.assertTrue(osVersion.equalsIgnoreCase("6.0"));
  }

  /**
   * Test get device name.
   *
   * @throws Exception the exception
   */
  @Test(groups = TestCategories.Appium)
  public void testGetDeviceName() throws Exception {
    String deviceName = AppiumConfig.getDeviceName();
    Assert.assertTrue(deviceName.equalsIgnoreCase("Android GoogleAPI Emulator"));
  }

  /**
   * Test get mobile hub url string.
   *
   * @throws Exception the exception
   */
  @Test(groups = TestCategories.Appium)
  public void testGetMobileHubUrlString() throws Exception {
    String mobileHubUrl = AppiumConfig.getMobileHubUrlString();
    Assert.assertTrue(mobileHubUrl.equalsIgnoreCase("http://ondemand.saucelabs.com:80/wd/hub"));
  }

  /**
   * Test get capabilities as strings.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetCapabilitiesAsStrings() {
    Map<String, String> capabilitiesAsStrings = AppiumConfig.getCapabilitiesAsStrings();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(capabilitiesAsStrings.containsKey(username));
    softAssert.assertEquals(capabilitiesAsStrings.get(username), "Partner_Magenic");
    softAssert.assertTrue(capabilitiesAsStrings.containsKey(accessKey));
    softAssert
        .assertEquals(capabilitiesAsStrings.get(accessKey), "0a4d7d84-f93b-43e6-9af1-1c89ac143355");
    softAssert.assertTrue(capabilitiesAsStrings.containsKey(browserName));
    softAssert.assertEquals(capabilitiesAsStrings.get(browserName), "Chrome");
    softAssert.assertTrue(capabilitiesAsStrings.containsKey(deviceOrientation));
    softAssert.assertEquals(capabilitiesAsStrings.get(deviceOrientation), "portrait");
    softAssert.assertAll();
  }

  /**
   * Test get capabilities as objects.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetCapabilitiesAsObjects() {
    Map<String, Object> capabilitiesAsObjects = AppiumConfig.getCapabilitiesAsObjects();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(capabilitiesAsObjects.containsKey(username));
    softAssert.assertEquals(capabilitiesAsObjects.get(username), "Partner_Magenic");
    softAssert.assertTrue(capabilitiesAsObjects.containsKey(accessKey));
    softAssert
        .assertEquals(capabilitiesAsObjects.get(accessKey), "0a4d7d84-f93b-43e6-9af1-1c89ac143355");
    softAssert.assertTrue(capabilitiesAsObjects.containsKey(browserName));
    softAssert.assertEquals(capabilitiesAsObjects.get(browserName), "Chrome");
    softAssert.assertTrue(capabilitiesAsObjects.containsKey(deviceOrientation));
    softAssert.assertEquals(capabilitiesAsObjects.get(deviceOrientation), "portrait");
    softAssert.assertAll();
  }

  /**
   * Test get save page source on fail.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetSavePageSourceOnFail() {
    Assert.assertFalse(AppiumConfig.getSavePageSourceOnFail());
  }

  /**
   * Test get soft assert screen shot.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetSoftAssertScreenShot() {
    Assert.assertFalse(AppiumConfig.getSoftAssertScreenShot());
  }

  /**
   * Test get command timeout.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetCommandTimeout() {
    Assert.assertEquals(AppiumConfig.getCommandTimeout().toMillis(), 122000);
  }

  /**
   * Test get command timeout error.
   */
  @Test(groups = TestCategories.Appium, expectedExceptions = NumberFormatException.class)
  @Ignore("Impacting future tests since there is no way to reload config")
  public void testGetCommandTimeoutError() {
    HashMap<String, String> configValues = new HashMap<>();
    configValues.put("MobileCommandTimeout", "sixty thousand");
    Config.addTestSettingValues(configValues, ConfigSection.AppiumMaqs, true);
    AppiumConfig.getCommandTimeout();
  }

  /**
   * Test get mobile timeout.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetMobileTimeout() {
    Assert.assertEquals(AppiumConfig.getMobileTimeout().toMillis(), 10000);
  }

  /**
   * Test get device type.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetDeviceType() {
    Assert.assertEquals(AppiumConfig.getDeviceType(), PlatformType.ANDROID);
  }

  /**
   * Test get device type android.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetDeviceTypeAndroid() {
    Assert.assertEquals(AppiumConfig.getDeviceType("android"), PlatformType.ANDROID);
  }

  /**
   * Test get device type ios.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetDeviceTypeIOS() {
    Assert.assertEquals(AppiumConfig.getDeviceType("ios"), PlatformType.IOS);
  }

  /**
   * Test get device type windows.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetDeviceTypeWindows() {
    Assert.assertEquals(AppiumConfig.getDeviceType("windows"), PlatformType.WINDOWS);
  }

  /**
   * Test get device type error.
   */
  @Test(groups = TestCategories.Appium, expectedExceptions = IllegalArgumentException.class)
  public void testGetDeviceTypeError() {
    AppiumConfig.getDeviceType("linux");
  }
}