/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber.steps;

import io.github.maqs.base.BaseTest;
import io.github.maqs.base.BaseTestObject;
import io.github.maqs.cucumber.ScenarioContext;
import io.github.maqs.utilities.logging.ILogger;

/**
 * Base generic cucumber step.
 */
public abstract class BaseGenericStep implements IBaseGenericStep {

  /**
   * Get the logger.
   *
   * @return The logger
   */
  public ILogger getLogger() {
    return ScenarioContext.get(ScenarioContext.MAQS_HOLDER, BaseTest.class).getLogger();
  }

  /**
   * Get the generic test object.
   *
   * @return The generic test object
   */
  public BaseTestObject getTestObject() {
    return (BaseTestObject) ScenarioContext.get(ScenarioContext.MAQS_HOLDER, BaseTest.class).getTestObject();
  }
}
