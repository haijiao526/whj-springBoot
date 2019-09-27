package com.whj.study.pojo;

import com.whj.study.utils.jpa.JpaEntityLister;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 数据库映射类基类
 * 封装主键生成，创建时间，更新时间等字段
 * 标注为@MappedSuperclass的类将不是一个完整的实体类,他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中
 * @author: WHJ
 * @create: 2019/9/5
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, JpaEntityLister.class})
public class BasePojo implements Serializable {
    /**
     * 主键生成策略
     */
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    private String id;

    /**
     * 创建时间有监听器自动维护
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creat_time", nullable = false)
    @CreatedDate
    protected Date createTime;

    /**
     * 更新时间有监听器自动维护
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date updateTime;

    @Column(name = "creat_name")
    private String creatName;
    @Column(name = "update_name")
    private String updateName;
}
