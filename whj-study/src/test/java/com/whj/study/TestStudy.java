package com.whj.study;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: ceshi
 * @author: WHJ
 * @create: 2019/9/11
 */

public class TestStudy extends BaseTest{
    @Autowired
    StudyConfigurationProperties properties;
    @Test
    public void ts(){
        properties.getCode();
    }
}
