/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber;

import io.github.maqs.base.BaseGenericTest;
import io.github.maqs.base.BaseTest;
import io.github.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseGenericCucumber class.
 */
public class BaseGenericCucumberUnitTest {

    /**
     * Verifies the specific base test object is a BaseGenericTest Object
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testCreateSpecificBaseTest() {
        BaseGenericCucumber genericCucumber = new BaseGenericCucumber();
        BaseTest testObject = genericCucumber.createSpecificBaseTest();
        Assert.assertTrue(testObject instanceof BaseGenericTest);
    }
}