package com.codebuffer.employee.Service;

import com.codebuffer.employee.Entity.Employee;
import com.codebuffer.employee.Exception.EmployeeNotfoundException;
import com.codebuffer.employee.Repository.EmployeeRepository;
import com.codebuffer.employee.VO.Department;
import com.codebuffer.employee.VO.ResponseTempleteVo;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmployeeService {
  @Autowired public RestTemplate restTemplate;
  @Autowired private EmployeeRepository employeeRepository;

  public Employee saveEmployee(Employee employee) {
    log.info("saving the employee");
    return employeeRepository.save(employee);
  }

  public Optional<Employee> fetchEmployee(Long id) throws EmployeeNotfoundException {
    log.info("fetching the employee by Id");
    return employeeRepository.findById(id);
  }

  public String removeEmployee(Long id) {
    log.info("removing the employee by ID");
    employeeRepository.deleteById(id);
    return "Employee removed";
  }

  public ResponseTempleteVo fetchEmployeeWithDepartment(Long employeeId) {
    log.info("retrieving the employee with Department");
    ResponseTempleteVo vo = new ResponseTempleteVo();
    Employee employee = employeeRepository.findByEmployeeId(employeeId);
    Department department =
        restTemplate.getForObject(
            "http://DEPARTMENT-SERVICE/departments/" + employee.getDepartmentId(),
            Department.class);
    vo.setDepartment(department);
    vo.setEmployee(employee);
    return vo;
  }
}
