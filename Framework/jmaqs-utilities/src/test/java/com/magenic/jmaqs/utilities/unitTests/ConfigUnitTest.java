/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.ConfigSection;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.HashMap;

/**
 * Configuration unit test class.
 */
public class ConfigUnitTest {
  /**
   * Test getting an entire section from the config
   */
  @Test
  public void getSectionWithConfigSecEnumTest() {
    HashMap<String, String> testSection = Config.getSection(ConfigSection.SeleniumMaqs);
    Assert.assertEquals(testSection.get("TestKey"), "testValueTwo");
    Assert.assertEquals(testSection.get("Browser"), "Internet Explorer");
  }

  /**
   * Test adding a list of test settings to the config
   */
  @Test
  public void addTestSettingValuesNewSectionTest() {
    HashMap<String, String> newValueMap = new HashMap();
    newValueMap.put("BROWSER1", "CHROME1");
    newValueMap.put("DBString2", "Dbstring2222");

    Config.addTestSettingValues(newValueMap,"NewSection", false);
    Assert.assertEquals(Config.getSection("NewSection").get("BROWSER1"), "CHROME1");
    Assert.assertEquals(Config.getSection("NewSection").get("DBString2"), "Dbstring2222");
  }

  /**
   * Test overriding existing values in the config
   */
  @Test
  public void addGeneralTestSettingValuesOverrideValuesTest() {
    HashMap<String, String> newValueMap = new HashMap();
    newValueMap.put("BrowserOverride", "CHROME");
    newValueMap.put("TimeoutOverride", "13333333");

    Config.addGeneralTestSettingValues(newValueMap, true);
    Assert.assertEquals(Config.getGeneralValue("BrowserOverride"), "CHROME");
    Assert.assertEquals(Config.getGeneralValue("TimeoutOverride"), "13333333");
  }

  /**
   * Test not overriding existing values in the config
   */
  @Test
  public void addGeneralTestSettingValuesDontOverrideValuesTest() {
    HashMap<String, String> newValueMap = new HashMap();
    newValueMap.put("DontBrowserOverride", "CHROME");
    newValueMap.put("DontTimeoutOverride", "13333333");

    HashMap<String, String> newValueMapTwo = new HashMap();
    newValueMapTwo.put("DontBrowserOverride", "IE");
    newValueMapTwo.put("DontTimeoutOverride", "5555");

    // add values to the override config since the values don't exist in the override config
    Config.addGeneralTestSettingValues(newValueMap, false);
    Assert.assertEquals(Config.getGeneralValue("DontBrowserOverride"), "CHROME");
    Assert.assertEquals(Config.getGeneralValue("DontTimeoutOverride"), "13333333");

    // don't add the values to the override config since the values do exist in the override config
    Config.addGeneralTestSettingValues(newValueMapTwo, false);
    Assert.assertEquals(Config.getGeneralValue("DontBrowserOverride"), "CHROME");
    Assert.assertEquals(Config.getGeneralValue("DontTimeoutOverride"), "13333333");

    // do add the values because of the override flag
    Config.addGeneralTestSettingValues(newValueMapTwo, true);
    Assert.assertEquals(Config.getGeneralValue("DontBrowserOverride"), "IE");
    Assert.assertEquals(Config.getGeneralValue("DontTimeoutOverride"), "5555");
  }

  /**
   * Test getting a value out of the default section of the config
   */
  @Test
  public void getGeneralValueTest() {
    Assert.assertEquals(Config.getGeneralValue("TestKey"), "testValue");
    Assert.assertEquals(Config.getGeneralValue("nonExistentKey", "defaultValue"), "defaultValue");
  }

  /**
   * Test getting a value of a specified section of the config
   */
  @Test
  public void getValueForSectionTest() {
    Assert.assertEquals(Config.getValueForSection("SeleniumMaqs", "TestKey"), "testValueTwo");
    Assert.assertEquals(Config.getValueForSection(ConfigSection.SeleniumMaqs, "Browser"), "Internet Explorer");
    Assert.assertEquals(Config.getValueForSection("SeleniumMaqs", "nonExistentKey", "defaultValue"), "defaultValue");
  }

  /**
   * Test getting a value from the config using the full defined path
   */
  @Test
  public void getValueTest() {
    Assert.assertEquals(Config.getValue("TestKey", "defaultValue"), "defaultValue");
    Assert.assertEquals(Config.getValue("SeleniumMaqs.TestKey"), "testValueTwo");
  }

  /**
   * Test checking if the key exists
   */
  @Test
  public void doesKeyExistTest() {
    Assert.assertTrue(Config.doesKeyExist("SeleniumMaqs.TestKey"));
    Assert.assertTrue(Config.doesGeneralKeyExist("TimeoutOverride"));
    Assert.assertTrue(Config.doesKeyExist("HubAddress", ConfigSection.SeleniumMaqs));
    Assert.assertFalse(Config.doesKeyExist("HubAddress", ConfigSection.MagenicMaqs));
  }
}
