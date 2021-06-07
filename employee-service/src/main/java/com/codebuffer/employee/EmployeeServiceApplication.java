package com.codebuffer.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// @EnableEurekaClient
@SpringBootApplication
public class EmployeeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmployeeServiceApplication.class, args);
  }

  // @LoadBalanced
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
