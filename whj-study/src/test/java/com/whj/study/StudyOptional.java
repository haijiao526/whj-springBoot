package com.whj.study;

import com.whj.study.pojo.Employee;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;


/**
 * @description:
 * @author: WHJ
 * @create: 2019/9/25
 */
public class StudyOptional {

    @Test
    public void studyOptional() {
        Employee employee = new Employee();
        employee.setAddress("甘肃兰州");
        Employee employee1 = (Employee) Optional.ofNullable(null).orElseGet(new Supplier<Employee>() {
            @Override
            public Employee get() {
                Employee employee = new Employee();
                employee.setAddress("甘肃兰州");
                return employee;
            }
        });
        System.out.println(employee1.getAddress());
    }
}
