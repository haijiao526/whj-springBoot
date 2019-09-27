package com.whj.study;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 学习@ConfigurationProperties注解:会直接从加载到容器中配置文件中寻相关的配置注入
 * prefix前缀最后的时属性字段
 * @author: WHJ
 * @create: 2019/9/11
 */
@ConfigurationProperties(prefix = "whj.study") //获取属性值
@Component
public class StudyConfigurationProperties {

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
