package com.whj.study.service.impl;

import com.whj.study.dao.BaseDao;
import com.whj.study.dao.DepartmentDao;
import com.whj.study.pojo.Department;
import com.whj.study.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 部门管理service实现类
 * @author: WHJ
 * @create: 2019/9/6
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public void insertIntoDB() {

        for (int i=0;i<=10;i++){
            Department department=new Department();
            department.setDeptCode(7001);
            department.setDeptName("开发部");
            department.setAdress("甘肃兰州");
            departmentDao.save(department);
        }
        //throw new RuntimeException("rtyui");
    }
}
