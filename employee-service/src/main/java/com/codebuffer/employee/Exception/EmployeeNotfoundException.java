package com.codebuffer.employee.Exception;

public class EmployeeNotfoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private String message;

  public EmployeeNotfoundException(String message) {
    super(message);
  }
}
