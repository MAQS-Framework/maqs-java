/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.utilities.helper.functionalinterfaces;

/**
 * The Action interface.
 * No param, no output functional interface.
 */
@FunctionalInterface
public interface Action {


  /**
   * invokes the functional interface when called.
   */
  void invoke();
}
