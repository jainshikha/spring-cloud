package com.codebuffer.employee.Exception;

import java.io.IOException;

public class EmployeeNotfoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private String message;

  public EmployeeNotfoundException(String message, IOException ex) {
    super(message);
  }
}
