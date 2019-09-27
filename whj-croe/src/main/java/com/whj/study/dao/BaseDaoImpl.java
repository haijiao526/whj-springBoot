package com.whj.study.dao;

import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @author zhushunfu
 * @createtime 2017年5月27日 下午3:13:27
 * @todo
 */
@NoRepositoryBean
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {


    protected static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

    private final EntityManager em;

    private final Class<T> entityClass;

    private final String entityName;

    /**
     * 构造函数
     */
    public BaseDaoImpl(final JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
        this.entityClass = entityInformation.getJavaType();
        this.entityName = entityInformation.getEntityName();
    }

    @Override
    public <E> List<E> listBySql(String sql, Class<E> clazz, Object... args) {
        return listBySql(sql, clazz, null, args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> List<E> listBySql(String sql, Class<E> clazz, Pageable pageable, Object... args) {
        Query query = em.createNativeQuery(sql);
        if (args != null && args.length > 0) {
            for (int index = 1, len = args.length; index <= len; index++) {
                query.setParameter(index, args[index - 1]);
            }
        }
        if (clazz.getName().equals(Map.class.getName())) {
            query.unwrap(SQLQuery.class).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        } else {
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));
        }
        if (pageable != null) {
            int pageNumber = pageable.getPageNumber();
            pageNumber = pageNumber < 0 ? 0 : pageNumber;
            query.setFirstResult(pageNumber * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> E queryForObject(String sql, Class<E> clazz, Object... args) {
        Query query = em.createNativeQuery(sql);
        if (args != null && args.length > 0) {
            for (int index = 1, len = args.length; index <= len; index++) {
                query.setParameter(index, args[index - 1]);
            }
        }
        if (clazz.getName().equals(Map.class.getName())) {
            query.unwrap(SQLQuery.class).setResultTransformer(CamelAliasToEntityMapResultTransformer.INSTANCE);
        } else {
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));
        }
        return (E) query.getSingleResult();
    }

    @Override
    public <E> Page<E> page(String sql, Class<E> contentClass, Pageable pageable, Object... args) {
        List<E> content = new ArrayList<E>();
        long total = count(sql, args);
        if (total > 0) {
            content = queryForList(sql, contentClass, pageable, args);
        }
        return new PageImpl<>(content, pageable, total);
    }


    private long count(String sql, Object... args) {
        String countSql = " SELECT COUNT(*) FROM (" + sql + ") x";
        Query query = em.createNativeQuery(countSql);
        if (args != null && args.length > 0) {
            for (int index = 1, len = args.length; index <= len; index++) {
                query.setParameter(index, args[index - 1]);
            }
        }
        Object result = query.getSingleResult();
        long total = 0;
        if (result != null) {
            total = NumberUtils.toLong(result.toString(), 0L);
        }
        return total;
    }

    @SuppressWarnings("unchecked")
    private <E> List<E> queryForList(String sql, Class<E> contentClass, Pageable pageable, Object... args) {
        Query query = em.createNativeQuery(sql);
        if (args != null && args.length > 0) {
            int index = 0;
            for (Object arg : args) {
                query.setParameter(++index, arg);
            }
        }
        if (contentClass.getName().equals(Map.class.getName())) {
            query.unwrap(SQLQuery.class).setResultTransformer(CamelAliasToEntityMapResultTransformer.INSTANCE);
        } else {
            query.unwrap(SQLQuery.class).setResultTransformer(new CamelAliasToBeanResultTransformer(contentClass));
        }
        if (pageable != null) {
            int pageNumber = pageable.getPageNumber();
            pageNumber = pageNumber < 0 ? 0 : pageNumber;
            query.setFirstResult(pageNumber * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }
        return query.getResultList();
    }

    @Override
    public T queryForEntity(String sql, Object... args) {
        return queryForObject(sql, entityClass, args);
    }

    @Override
    public Page<T> page(String sql, Pageable pageable, Object... args) {
        return page(sql, entityClass, pageable, args);
    }

    class CamelAliasToBeanResultTransformer extends AliasToBeanResultTransformer {

        private static final long serialVersionUID = 3553014390988933325L;

        @SuppressWarnings("rawtypes")
        private CamelAliasToBeanResultTransformer(Class resultClass) {
            super(resultClass);
        }

        @Override
        public Object transformTuple(Object[] tuple, String[] aliases) {
            for (int i = 0, len = aliases.length; i < len; i++) {
                if (aliases[i].contains("_")) {
                    aliases[i] = underscore2camel(aliases[i]);
                }
            }
            return super.transformTuple(tuple, aliases);
        }


    }

    static class CamelAliasToEntityMapResultTransformer extends AliasedTupleSubsetResultTransformer {

        private static final long serialVersionUID = -6352270460696818277L;

        public final static CamelAliasToEntityMapResultTransformer INSTANCE = new CamelAliasToEntityMapResultTransformer();

        private CamelAliasToEntityMapResultTransformer() {
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        @Override
        public Object transformTuple(Object[] tuple, String[] aliases) {
            Map result = new HashMap(tuple.length);
            for (int i = 0; i < tuple.length; i++) {
                String alias = aliases[i];
                if (alias != null) {
                    result.put(underscore2camel(alias), tuple[i]);
                }
            }
            return result;
        }

        @Override
        public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
            return false;
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    private static String underscore2camel(String underscoreName) {
        underscoreName = underscoreName.toLowerCase();
        String[] sections = underscoreName.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sections.length; i++) {
            String s = sections[i];
            if (i == 0) {
                sb.append(s);
            } else {
                sb.append(capitalize(s));
            }
        }
        return sb.toString();
    }

    private static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
                .toString();
    }

    public EntityManager getEm() {
        return em;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public String getEntityName() {
        return entityName;
    }

}
