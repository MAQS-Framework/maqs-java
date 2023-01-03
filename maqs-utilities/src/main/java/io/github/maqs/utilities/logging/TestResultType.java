/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.utilities.logging;

/**
 * The Test Result Type enum class.
 */
public enum TestResultType {

  /**
   * The test passed.
   */
  PASS,

  /**
   * The test failed.
   */
  FAIL,

  /**
   * The test was inconclusive.
   */
  INCONCLUSIVE,

  /**
   * The test was skipped.
   */
  SKIP,

  /**
   * The test had an unexpected result.
   */
  OTHER,
}
