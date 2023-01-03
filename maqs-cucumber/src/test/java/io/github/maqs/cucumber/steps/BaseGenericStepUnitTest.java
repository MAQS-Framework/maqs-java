/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber.steps;

import io.github.maqs.base.BaseGenericTest;
import io.github.maqs.cucumber.ScenarioContext;
import io.github.maqs.cucumber.unittestpagemodel.DummyBaseGenericStep;
import io.github.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseGenericStep class.
 */
public class BaseGenericStepUnitTest extends BaseGenericTest {

    /**
     * Verifies the Logger is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetLogger() {
        DummyBaseGenericStep stepClass = new DummyBaseGenericStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getLogger(), "Checking that Logger is not null through BaseGenericStep");
    }

    /**
     * Verifies the Test Object is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetTestObject() {
        DummyBaseGenericStep stepClass = new DummyBaseGenericStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getTestObject(), "Checking that Test Object is not null through BaseGenericStep");
    }
}