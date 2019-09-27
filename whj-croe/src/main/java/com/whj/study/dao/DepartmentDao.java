package com.whj.study.dao;

import com.whj.study.pojo.Department;

/**
 * @Description: 部门管理dao层
 * @Author: WHJ
 */
public interface DepartmentDao extends BaseDao<Department, String> {

    public Department findByDeptCode(Integer deptCode);
}
