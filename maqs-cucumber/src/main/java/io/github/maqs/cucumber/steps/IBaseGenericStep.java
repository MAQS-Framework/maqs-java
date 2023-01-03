/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber.steps;

import io.github.maqs.base.BaseTestObject;
import io.github.maqs.utilities.logging.ILogger;

public interface IBaseGenericStep {

  /**
   * Get the logger.
   *
   * @return The logger
   */
  ILogger getLogger();

  /**
   * Get the generic test object.
   *
   * @return The generic test object
   */
  BaseTestObject getTestObject();
}
