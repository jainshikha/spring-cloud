package com.codebuffer.employee.Controller;

import com.codebuffer.employee.Entity.Employee;
import com.codebuffer.employee.Exception.EmployeeNotfoundException;
import com.codebuffer.employee.Service.EmployeeService;
import com.codebuffer.employee.VO.ResponseTempleteVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Employee")
public class EmployeeController {
  @Autowired private EmployeeService employeeService;

  @PostMapping("/")
  public Employee saveEmployee(@Valid @RequestBody Employee employee) {
    return employeeService.saveEmployee(employee);
  }

  /*swagger documentation via annotations*/
  @Operation(summary = "Get a Employee by its id")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found the employee",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
      })
  @GetMapping("/{id}")
  public Optional<Employee> getEmployee(@PathVariable("id") Long id)
      throws EmployeeNotfoundException {
    try {
      Optional<Employee> e = employeeService.fetchEmployee(id);
      if (!e.isPresent()) {
        throw new EmployeeNotfoundException("Employee not found. ");
      }
      return e;
    } catch (RuntimeException e) {
      throw new RuntimeException("Caught run time exception. Exception is: ", e);
    }
  }

  /*@vaild to vaildate the entity while input*/
  @PutMapping("/{id}")
  public Employee updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody Employee employee)
      throws EmployeeNotfoundException {
    Optional<Employee> emp = employeeService.fetchEmployee(id);
    if (!emp.isPresent()) {
      emp.get().setEmployeeFirstName(employee.getEmployeeFirstName());
      emp.get().setEmployeeLastName(employee.getEmployeeLastName());
      emp.get().setEmployeeEmail(employee.getEmployeeEmail());
      emp.get().setEmployeePassword(employee.getEmployeePassword());
      emp.get().setEmployeeMobileNo(employee.getEmployeeMobileNo());

      return employeeService.saveEmployee(emp.get());
    } else {
      throw new EmployeeNotfoundException("Employee does not Exists. ");
    }
  }

  @DeleteMapping("/{id}")
  public String deleteEmployee(@PathVariable("id") Long id) {
    return employeeService.removeEmployee(id);
  }

  @GetMapping("/departments/{id}")
  public ResponseTempleteVo getEmployeeWithDepartment(@PathVariable("id") Long employeeId) {
    return employeeService.fetchEmployeeWithDepartment(employeeId);
  }
}
