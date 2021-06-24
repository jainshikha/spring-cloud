package com.codebuffer.employee.AOP;

import com.codebuffer.employee.Entity.Employee;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckService {
    @Pointcut("execution(public com.codebuffer.employee.Entity.Employee saveEmployee(*))")
    public void checkMethod() {
    }

    @Before("checkMethod()")
    public void checkValidation() throws Exception {
        try {
            System.out.println("checking the validation before the execution happens");
            int x = 1 / 0;
        } catch (Exception e) {
            throw new Exception("checking excpetion");
        }
    }
}
