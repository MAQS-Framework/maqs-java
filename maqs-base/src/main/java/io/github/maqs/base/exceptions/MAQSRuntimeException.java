/*
 * Copyright 2022 (C) MAQS, All rights Reserved
 */

package io.github.maqs.base.exceptions;

public class MAQSRuntimeException extends RuntimeException {

  public MAQSRuntimeException(String message, Exception exception) {
    super(message, exception);
  }

  public MAQSRuntimeException(String message) {
    super(message);
  }
}

