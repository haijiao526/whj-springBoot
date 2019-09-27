package com.whj.study.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;


import java.io.Serializable;
import java.util.List;

/**
 * @description: service基类
 * @author: WHJ
 * @create: 2019/9/6
 */
public interface BaseService<T, ID extends Serializable> {


    /**
     * 获取当前类泛型实体类型Class
     *
     * @return
     */
    Class<T> getEntityClass();

    /**
     * 通过ID获取
     * 直接初始化级联对象
     *
     * @param id
     * @return
     */
    T getById(ID id);

    /**
     * Get read-only entity, disable state snapshots and dirty checking
     *
     * @param id
     * @param clazz
     * @return
     */
    T getByIdReadOnly(ID id, Class<T> clazz);

    /**
     * 通过动态条件查找
     *
     * @param specification
     * @return
     */
    T getBySpecification(Specification<T> specification);

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<T> getPage(Pageable pageable);

    /**
     * 动态条件分页查询
     *
     * @param specification
     * @return
     */
    Page<T> getPage(Specification<T> specification, Pageable pageable);

    /**
     * 查询所有记录
     *
     * @return
     */
    List<T> getList();

    /**
     * 根据ids获取所有的对象集合
     *
     * @param ids
     * @return
     */
    List<T> getList(Iterable<ID> ids);

    /**
     * 查询所有记录，返回按规则排序的列表
     *
     * @param sort
     * @return
     */
    List<T> getList(Sort sort);

    /**
     * 动态条件查询
     *
     * @param spec
     * @return
     */
    List<T> getList(Specification<T> spec);

    /**
     * 动态条件查询，返回按规则排序的列表
     *
     * @param spec
     * @param sort
     * @return
     */
    List<T> getList(Specification<T> spec, Sort sort);

    /**
     * 查询指定字段
     *
     * @param clazz      类类型
     * @param fieldNames 需要查询的字段
     * @param spec       查询条件
     * @param sort       排序规则
     * @return
     */
    <Y> List<Y> getProjection(Class<T> clazz, Class<Y> resultClass, List<String> fieldNames, Specification<T> spec,
                              Sort sort);

    /**
     * 通过ID判断是否存在
     *
     * @param id
     * @return
     */
    boolean exists(ID id);

    /**
     * 查询数量
     *
     * @return
     */
    long count();

    /**
     * 动态条件查询数量
     *
     * @param specification
     * @return
     */
    long count(Specification<T> specification);


    /**
     * 保存
     *
     * @param entity
     */
    T save(T entity);

    /**
     * 批量保存
     *
     * @param entities
     * @return
     */
    List<T> save(Iterable<T> entities);

    /**
     * 同步缓存数据到数据库
     */
    void flush();

    /**
     * 保存并同步缓存数据到数据库
     *
     * @param entity
     */
    T saveAndFlush(T entity);

    /**
     * 通过ID删除
     *
     * @param id
     */
    void delete(ID id);

    /**
     * 删除实体
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 批量删除
     *
     * @param entities
     */
    void delete(Iterable<T> entities);

    /**
     * 通过ID批量删除
     *
     * @param ids
     */
    void deleteByIds(Iterable<ID> ids);

    /**
     * 批量删除
     *
     * @param entities
     */
    void deleteInBatch(Iterable<T> entities);

    /**
     * 删除所有
     */
    void deleteAll();

    void deleteAllInBatch();

    /**
     * Evict persistent instance from the persistence context
     *
     * @param obj
     */
    void detach(T obj);

    void clear();
}
