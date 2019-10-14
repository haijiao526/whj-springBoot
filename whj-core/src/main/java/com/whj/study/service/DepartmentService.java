package com.whj.study.service;

import com.whj.study.pojo.Department;

/**
 * @Description: 部门管理接口
 * @Author: WHJ
 */
public interface DepartmentService extends BaseService<Department, String> {

    public Department getBydeptCode(Integer deptCode);

    void insertIntoDB();
}
