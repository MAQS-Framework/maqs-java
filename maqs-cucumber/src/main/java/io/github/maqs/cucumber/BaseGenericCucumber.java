/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.cucumber;

import io.github.maqs.base.BaseGenericTest;
import io.github.maqs.base.BaseTest;

/**
 * The base generic cucumber object.
 */
public class BaseGenericCucumber extends BaseCucumberTestNG {

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



