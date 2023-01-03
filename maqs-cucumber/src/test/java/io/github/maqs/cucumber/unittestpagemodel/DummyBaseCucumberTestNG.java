/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber.unittestpagemodel;

import io.github.maqs.base.BaseGenericTest;
import io.github.maqs.base.BaseTest;
import io.github.maqs.cucumber.BaseCucumberTestNG;

/**
 * A Dummy BaseCucumberTestNG class for testing
 */
public class DummyBaseCucumberTestNG extends BaseCucumberTestNG {
    /**
     * Create a test object.
     *
     * @return A generic base test object
     */
    @Override
    public BaseTest createSpecificBaseTest() {
        return new BaseGenericTest();
    }
}
