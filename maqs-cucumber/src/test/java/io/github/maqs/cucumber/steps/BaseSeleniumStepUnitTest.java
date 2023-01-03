/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber.steps;

import io.github.maqs.cucumber.ScenarioContext;
import io.github.maqs.cucumber.unittestpagemodel.DummyBaseSeleniumStep;
import io.github.maqs.selenium.BaseSeleniumTest;
import io.github.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseSeleniumStep class.
 */
public class BaseSeleniumStepUnitTest extends BaseSeleniumTest {

    /**
     * Verifies the Driver is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetDriver() {
        DummyBaseSeleniumStep stepClass = new DummyBaseSeleniumStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getDriver(), "Checking that Selenium Driver is not null through BaseSeleniumStep");
    }

    /**
     * Verifies the Test Object is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetTestObject() {
        DummyBaseSeleniumStep stepClass = new DummyBaseSeleniumStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getTestObject(), "Checking that Test Object is not null through BaseSeleniumStep");
    }
}