package com.whj.study.TransactionStudy;

import com.whj.study.BaseTest;
import com.whj.study.pojo.Department;
import com.whj.study.service.DepartmentService;
import org.junit.Test;
import org.omg.CORBA.FloatSeqHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 数据库插入大量的数据
 * @author: WHJ
 * @create: 2019/9/29
 */
public class InsertSQL extends BaseTest {

    @Autowired
    DepartmentService departmentService;
    @Test
    public void insertIntoDB(){
        departmentService.insertIntoDB();
    }
}
