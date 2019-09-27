package com.whj.study.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @description: dao层基类
 * @author: WHJ
 * JpaRepository:简单的curd
 * JpaSpecificationExecutor:可以实现复杂的查询如由sql
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {


    <E> List<E> listBySql(String sql, Class<E> clazz, Object... args);

    /**
     * 分页查询
     *
     * @param sql      sql语句
     * @param clazz    要返回的对象类型
     * @param pageable 分页参数(注:第一页pageNumber为0)
     * @param args     sql语句的参数
     * @return
     */
    <E> List<E> listBySql(String sql, Class<E> clazz, Pageable pageable, Object... args);

    /**
     * 根据sql查询实体类
     *
     * @param sql
     * @param args
     * @return
     */
    T queryForEntity(String sql, Object... args);

    /**
     * 根据sql返回clazz的对象
     *
     * @param sql
     * @param clazz
     * @param args
     * @return
     */
    <E> E queryForObject(String sql, Class<E> clazz, Object... args);

    /**
     * 分页查询
     *
     * @param sql
     * @param contentClass
     * @param pageable     分页参数(注:第一页pageNumber为0)
     * @param args
     * @return
     */
    <E> Page<E> page(String sql, Class<E> contentClass, Pageable pageable, Object... args);

    /**
     * 分页查询
     *
     * @param sql
     * @param pageable
     * @param args
     * @return
     */
    Page<T> page(String sql, Pageable pageable, Object... args);
}
