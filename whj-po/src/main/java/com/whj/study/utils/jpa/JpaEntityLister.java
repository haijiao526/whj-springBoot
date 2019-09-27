package com.whj.study.utils.jpa;

import com.whj.study.pojo.BasePojo;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 被@Prepersist注解的方法 ，完成save之前的操作。
 * 被@Preupdate注解的方法 ，完成update之前的操作。
 * 被@PreRemove注解的方法 ，完成remove之前的操作。
 * 被@Postpersist注解的方法 ，完成save之后的操作。
 * 被@Postupdate注解的方法 ，完成update之后的操作。
 * 被@PostRemovet注解的方法 ，完成remove之后的操作。
 *
 * @description: Jpa数据库映射类监听，可以对保存，更新等操作监听
 * @author: WHJ
 * @create: 2019/9/5
 */
public class JpaEntityLister {

    /**
     * save之前给basePojo中的创建人赋值
     *
     * @param basePojo 映射基类对象
     */
    @PrePersist
    private void preCreate(BasePojo basePojo) {
        //可以获取当前登录人set进去
        basePojo.setCreatName("whj");
    }

    /**
     * update之前给basePojo中的更新人赋值
     *
     * @param basePojo 映射基类对象
     */
    @PreUpdate
    private void preUpdate(BasePojo basePojo) {
        //可以获取当前登录人set进去
        basePojo.setUpdateName("007");
    }
}
