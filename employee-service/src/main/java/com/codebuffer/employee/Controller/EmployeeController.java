package com.codebuffer.employee.Controller;

import com.codebuffer.employee.Entity.Employee;
import com.codebuffer.employee.Service.EmployeeService;
import com.codebuffer.employee.VO.ResponseTempleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public ResponseTempleteVo getEmployeeWithDepartment(@PathVariable("id") Long employeeId) {
        return employeeService.fetchEmployeeWithDepartment(employeeId);
    }
}
