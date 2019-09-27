package com.whj.study.service.impl;

import com.whj.study.dao.BaseDao;
import com.whj.study.dao.DepartmentDao;
import com.whj.study.pojo.Department;
import com.whj.study.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 部门管理service实现类
 * @author: WHJ
 * @create: 2019/9/6
 */
@Service
@Slf4j
public class DepartmentServiceImpl extends BaseServiceImpl<Department, String> implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public BaseDao<Department, String> getGenericDao() {
        return this.departmentDao;
    }

    @Override
    public Department getBydeptCode(Integer deptCode) {
        log.info("通过deptCode查询部门信息");
        return departmentDao.findByDeptCode(deptCode);
    }
}
