/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package com.maqs.cucumber;

import com.maqs.base.BaseGenericTest;
import com.maqs.base.BaseTest;
import com.maqs.cucumber.unittestpagemodel.DummyBaseCucumberTestNG;
import com.maqs.cucumber.unittestpagemodel.DummyTestResult;
import com.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseCucumberTestNG class.
 */
public class BaseCucumberTestNGUnitTest extends BaseGenericTest {

    /**
     * Verifies the before method properly sets the Scenario Context
     */
    @Test(groups = TestCategories.CUCUMBER, singleThreaded = true, priority = 0)
    public void testBeforeMethod() {
        DummyBaseCucumberTestNG dummyCucumber = new DummyBaseCucumberTestNG();
        String[] dummyArgs = {"Dummy Arg 1", "Dummy Arg 2"};

        Assert.assertNull(ScenarioContext.get(ScenarioContext.MAQS_HOLDER));
        dummyCucumber.beforeMethod(null, dummyArgs, this.getTestContext());
        Assert.assertNotNull(ScenarioContext.get(ScenarioContext.MAQS_HOLDER));
    }

    /**
     * Verifies the tear down process correctly cleans up the Scenario Context
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testTearDown() {
        DummyBaseCucumberTestNG dummyCucumber = new DummyBaseCucumberTestNG();
        BaseTest dummyTest = dummyCucumber.createSpecificBaseTest();
        String[] dummyArgs = {"Dummy Arg 1", "Dummy Arg 2"};

        dummyCucumber.beforeMethod(null, dummyArgs, dummyTest.getTestContext());
        DummyTestResult dummyResult = new DummyTestResult(dummyTest.getTestContext());
        dummyCucumber.tearDown(dummyResult);

        Assert.assertNull(ScenarioContext.get(ScenarioContext.MAQS_HOLDER));
    }

    /**
     * Verifies the test name is properly set
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetTestName() {
        DummyBaseCucumberTestNG dummyCucumber = new DummyBaseCucumberTestNG();
        String[] dummyArgs = {"Dummy Arg 1", "Dummy Arg 2"};

        dummyCucumber.beforeMethod(null, dummyArgs, this.getTestContext());

        String testName = dummyCucumber.getTestName();
        Assert.assertEquals(testName, String.format("%s - %s", dummyArgs[1], dummyArgs[0]));
    }

    /**
     * Verifies the specific base test object is a BaseGeneric Object
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testCreateSpecificBaseTest() {
        DummyBaseCucumberTestNG dummyCucumber = new DummyBaseCucumberTestNG();
        BaseTest testObject = dummyCucumber.createSpecificBaseTest();
        Assert.assertTrue(testObject instanceof BaseGenericTest);
    }

    /**
     * Verifies the scenarios are not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testScenarios() {
        DummyBaseCucumberTestNG dummyCucumber = new DummyBaseCucumberTestNG();
        Assert.assertNotNull(dummyCucumber.scenarios());
    }
}