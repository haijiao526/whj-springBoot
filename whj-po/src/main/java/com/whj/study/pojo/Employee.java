package com.whj.study.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @description: 员工类
 * @author: WHJ
 * @create: 2019/9/5
 */
@Entity
@Setter
@Getter
@Table(name = "employee")
public class Employee extends BasePojo {

    @Column(name = "emp_name", nullable = false, length = 20)
    private String empName;
    @Column(name = "emp_age", length = 3)
    private Integer age;
    @Column(name = "emp_phone", nullable = false, length = 11)
    private String phone;
    @Column(name = "emp_address", nullable = false, length = 50)
    private String address;
    /**
     * 多对一
     * "department_id"为外键关联
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_code")
    private Department department;
}
