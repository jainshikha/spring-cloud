package com.codebuffer.employee.VO;

import com.codebuffer.employee.Entity.Employee;
import lombok.Data;

@Data
public class ResponseTempleteVo {
    private Employee employee;
    private Department department;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
