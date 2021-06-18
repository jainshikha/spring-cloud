package com.codebuffer.employee.Entity;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import org.springframework.lang.NonNull;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long employeeId;

  @NonNull private String employeeFirstName;
  @NonNull private String employeeLastName;
  @Email private String employeeEmail;
  private String employeePassword;
  private String employeeMobileNo;
  private Long departmentId;
  @OneToMany private List<Address> address;

  public Employee() {}

  public Employee(
      Long employeeId,
      @NonNull String employeeFirstName,
      @NonNull String employeeLastName,
      @Email String employeeEmail,
      String employeePassword,
      String employeeMobileNo,
      Long departmentId,
      List<Address> address) {
    this.employeeId = employeeId;
    this.employeeFirstName = employeeFirstName;
    this.employeeLastName = employeeLastName;
    this.employeeEmail = employeeEmail;
    this.employeePassword = employeePassword;
    this.employeeMobileNo = employeeMobileNo;
    this.departmentId = departmentId;
    this.address = address;
  }

  public List<Address> getAddress() {
    return address;
  }

  public void setAddress(List<Address> address) {
    this.address = address;
  }

  public String getEmployeePassword() {
    return employeePassword;
  }

  public void setEmployeePassword(String employeePassword) {
    this.employeePassword = employeePassword;
  }

  public String getEmployeeMobileNo() {
    return employeeMobileNo;
  }

  public void setEmployeeMobileNo(String employeeMobileNo) {
    this.employeeMobileNo = employeeMobileNo;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeFirstName() {
    return employeeFirstName;
  }

  public void setEmployeeFirstName(String employeeFirstName) {
    this.employeeFirstName = employeeFirstName;
  }

  public String getEmployeeLastName() {
    return employeeLastName;
  }

  public void setEmployeeLastName(String employeeLastName) {
    this.employeeLastName = employeeLastName;
  }

  public String getEmployeeEmail() {
    return employeeEmail;
  }

  public void setEmployeeEmail(String employeeEmail) {
    this.employeeEmail = employeeEmail;
  }

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }
}
