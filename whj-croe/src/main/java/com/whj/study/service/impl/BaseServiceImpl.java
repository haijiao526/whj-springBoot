package com.whj.study.service.impl;

import com.whj.study.dao.BaseDao;
import com.whj.study.service.BaseService;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @description: serviceImpl基类
 * @author: WHJ
 */
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    @PersistenceContext
    protected EntityManager em;

    public abstract BaseDao<T, ID> getGenericDao();

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> getEntityClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<T>) params[0];
    }

    @Override
    public T getById(ID id) {
        return getGenericDao().findOne(id);
    }

    @Override
    public T getByIdReadOnly(ID id, Class<T> clazz) {
        boolean defaultReadOnly = em.unwrap(Session.class).isDefaultReadOnly();
        em.unwrap(Session.class).setDefaultReadOnly(true);
        T t = em.find(clazz, id);
        em.unwrap(Session.class).setDefaultReadOnly(defaultReadOnly);
        return t;
    }

    @Override
    public T getBySpecification(Specification<T> specification) {
        return getGenericDao().findOne(specification).get();
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return getGenericDao().findAll(pageable);
    }

    @Override
    public Page<T> getPage(Specification<T> specification, Pageable pageable) {
        return getGenericDao().findAll(specification, pageable);
    }

    @Override
    public List<T> getList() {
        return getGenericDao().findAll();
    }

    @Override
    public List<T> getList(Iterable<ID> ids) {
        return getGenericDao().findAll(ids);
    }

    @Override
    public List<T> getList(Specification<T> specification) {
        return getGenericDao().findAll(specification);
    }

    @Override
    public List<T> getList(Sort sort) {
        return getGenericDao().findAll(sort);
    }

    @Override
    public List<T> getList(Specification<T> specification, Sort sort) {
        return getGenericDao().findAll(specification, sort);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <Y> List<Y> getProjection(Class<T> clazz, Class<Y> resultClass, List<String> fieldNames,
                                     Specification<T> spec, Sort sort) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery criteria = cb.createQuery();
        Root<T> root = criteria.from(clazz);

        if (spec != null) {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            Predicate predicate = spec.toPredicate(root, criteria, builder);
            if (predicate != null) {
                criteria.where(predicate);
            }
        }

        int size = fieldNames.size();
        Selection<?>[] selectionArr = new Selection<?>[size];
        for (int i = 0; i < size; i++) {
            selectionArr[i] = root.get(fieldNames.get(i));
        }
        criteria.select(cb.construct(resultClass, selectionArr));

        if (sort != null) {
            criteria.orderBy(QueryUtils.toOrders(sort, root, cb));
        }

        return em.createQuery(criteria).getResultList();
    }

    @Override
    public boolean exists(ID id) {
        return getGenericDao().exists(id);
    }

    @Override
    public long count() {
        return getGenericDao().count();
    }

    @Override
    public long count(Specification<T> specification) {
        return getGenericDao().count(specification);
    }

    @Override
    @Transactional
    public T save(T entity) {
        return getGenericDao().save(entity);
    }

    @Override
    @Transactional
    public List<T> save(Iterable<T> entities) {
        return getGenericDao().save(entities);
    }

    @Override
    @Transactional
    public void flush() {
        getGenericDao().flush();
    }

    @Override
    @Transactional
    public T saveAndFlush(T entity) {
        return getGenericDao().saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        getGenericDao().delete(id);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        getGenericDao().delete(entity);
    }

    @Override
    @Transactional
    public void delete(Iterable<T> entities) {
        getGenericDao().delete(entities);
    }

    @Override
    @Transactional
    public void deleteByIds(Iterable<ID> ids) {
        for (ID id : ids) {
            delete(id);
        }
    }

    @Override
    @Transactional
    public void deleteInBatch(Iterable<T> entities) {
        getGenericDao().deleteInBatch(entities);
    }

    @Override
    @Transactional
    public void deleteAll() {
        getGenericDao().deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllInBatch() {
        getGenericDao().deleteAllInBatch();
    }

    @Override
    public void detach(T obj) {
        em.detach(obj);
    }

    @Override
    public void clear() {
        em.clear();
    }
}
