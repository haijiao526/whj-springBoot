package com.whj.study.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @description: 部门信息
 * @author: WHJ
 * @create: 2019/9/4
 */

@Setter
@Getter
@Entity
@Table(name = "department")
public class Department extends BasePojo {

    @Column(name = "dept_name",length = 20)
    private String deptName;

    @Column(name = "dept_adress")
    private String adress;

    @Column(name = "dept_lender",length = 20)
    private String lender;

    @Column(name = "dept_code",length = 4,unique = false,nullable = false)
    private Integer deptCode;
    /**
     * 一对多
     * cascade = CascadeType.ALL 级联操作
     * JoinColumn 关联外键
     * FetchType.LAZY 懒加载
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_code")
    private List<Employee> employee;
}
