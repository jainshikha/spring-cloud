package com.codebuffer.employee.Controller;

import com.codebuffer.employee.Entity.Employee;
import com.codebuffer.employee.Exception.EmployeeNotfoundException;
import com.codebuffer.employee.Service.EmployeeService;
import com.codebuffer.employee.VO.ResponseTempleteVo;
import com.codebuffer.employee.util.FileUploadHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("Employee")
public class EmployeeController {
  @Autowired private EmployeeService employeeService;

  @Autowired private FileUploadHelper fileUploadHelper;

  @PostMapping(value = "/")
  public Employee saveEmployee(@Valid @RequestBody Employee employee) {
    return employeeService.saveEmployee(employee);
  }

  /*image as input*/
  @PostMapping(value = "/upload")
  public String uploadEmpimage(@RequestParam("file") MultipartFile file) {
    if (!file.isEmpty() && Objects.equals(file.getContentType(), "image/jpeg")) {
      fileUploadHelper.uploadFile(file);
      return "file uploaded successfully";
    } else {
      return "Attached file is empty or the file type is not jpeg";
    }
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
        throw new EmployeeNotfoundException("Employee not found. ", new IOException());
      }
      return e;
    } catch (RuntimeException e) {
      throw new RuntimeException("Caught run time exception. Exception is: ", e);
    }
  }

  /*
  sample URL : localhost:9002/Employee?size=5?page=20
  */
  @GetMapping()
  public Page<Employee> getAllEmployee(
      @RequestParam(defaultValue = "3", required = false) int size,
      @RequestParam(defaultValue = "0", required = false) int page)
      throws EmployeeNotfoundException {
    try {
      Pageable paging = PageRequest.of(page, size);
      Page<Employee> e = employeeService.fetchAllEmployee(paging);
      new Thread(() -> System.out.println("multiple time from thread"));
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
      throw new EmployeeNotfoundException("Employee does not Exists. ", new IOException());
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
