/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.utilities.logging;

/**
 * The Logging Enabled enum class.
 * Sets when and if to log the message.
 */
public enum LoggingEnabled {

  /**
   * Yes log.
   */
  YES,

  /**
   * Only save a log when there is a failure.
   */
  ONFAIL,

  /**
   * No, don't log.
   */
  NO,
}