package com.whj.study.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @description: 角色表
 * @author: WHJ
 * @create: 2019/9/5
 */
@Entity
@Table(name = "role")
@Setter
@Getter
public class Role extends BasePojo{

    @Column(name = "role_code", nullable = false, length = 4)
    private String roleCode;
    @Column(name = "role_name", nullable = false, length = 20)
    private String roleName;
}
