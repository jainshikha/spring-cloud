package com.codebuffer.employee.Repository;

import com.codebuffer.employee.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  //  @Query("SELECT u FROM Employee u WHERE u.employeeId = 1")
  Employee findByEmployeeId(Long employeeId);

  Page<Employee> findAll(Pageable paging);
}
