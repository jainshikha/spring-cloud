package com.codebuffer.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
    @GetMapping("/employeeServiceFallBack")
    public String employeeFallBack() {
        return "employee Service is taking longer then expected, please try again later ";
    }
    @GetMapping("/departmentServiceFallBack")
    public String departmentFallBack() {
        return "employee Service is taking longer then expected, please try again later ";
    }
}
