/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.base.exceptions;

/**
 * The type Manager disposal exception.
 */
public class ManagerDisposalException extends RuntimeException {
  /**
   * Instantiates a new Manager disposal exception.
   *
   * @param e the exception
   */
  public ManagerDisposalException(Exception e) {
    super(e);
  }
}
