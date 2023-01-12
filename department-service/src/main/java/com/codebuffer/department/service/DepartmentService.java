package com.codebuffer.department.service;

import com.codebuffer.department.entity.Department;
import com.codebuffer.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;


    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> fetchDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }
}
