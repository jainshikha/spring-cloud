package com.codebuffer.employee.Repository;

import com.codebuffer.employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Employee findByEmployeeId(Long employeeId);
}
