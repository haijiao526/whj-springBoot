package com.whj.study.testPojo;

import com.whj.study.BaseTest;
import com.whj.study.pojo.Department;
import com.whj.study.service.DepartmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @description: 员工类测试
 * @author: WHJ
 * @create: 2019/9/6
 */
public class DepartmentTest extends BaseTest {
    @Autowired
    DepartmentService departmentService;

    @Rollback(false)
    @Test
    public void saveDepartment() {
        // Department department = departmentService.getBydeptCode(7001);
        Department department = new Department();
        department.setAdress("甘肃兰州");
        department.setDeptName("研发部");
        department.setLender("王小小");
        department.setDeptCode(7001);
        departmentService.save(department);

        System.out.println("测试部门数据插入");
    }
}
