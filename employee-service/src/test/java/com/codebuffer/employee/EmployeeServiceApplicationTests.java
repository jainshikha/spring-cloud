package com.codebuffer.employee;

import static org.junit.Assert.assertEquals;

import com.codebuffer.employee.Entity.Address;
import com.codebuffer.employee.Entity.Employee;
import com.codebuffer.employee.Repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeServiceApplicationTests {
  @Autowired private EmployeeRepository repository;

  @Test
  void whenValidName_thenSaveEmployee() {
    List<Address> address = new ArrayList<>();
    address.add(new Address(1L, "itwari", "chhindwara", "MP"));
    Employee alexEmp =
        new Employee(1L, "alex", "zender", "shikha@gmail.com", "pass", "7755933103", 1l, address);
    Employee savedEmp = repository.save(alexEmp);
    assertEquals(savedEmp.getEmployeeFirstName(), alexEmp.getEmployeeFirstName());
  }

  @Test
  void whenValidName_thenEmployeeShouldBeFound() {
    List<Address> address = new ArrayList<>();
    address.add(new Address(1L, "itwari", "chhindwara", "MP"));
    Employee alexEmp =
        new Employee(1L, "alex", "zender", "shikha@gmail.com", "pass", "7755933103", 1l, address);
    repository.save(alexEmp);
    Optional<Employee> foundEmp = repository.findById(1L);
    assertEquals(foundEmp.get().getEmployeeFirstName(), alexEmp.getEmployeeFirstName());
  }
}
