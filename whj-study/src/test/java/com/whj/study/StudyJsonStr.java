package com.whj.study;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: WHJ
 * @create: 2019/9/14
 */
public class StudyJsonStr {

    @Test
    public void studyTest() {

        JsonStudy jsonStudy = new JsonStudy();
        List<JsonStudy.Study> studyList = new ArrayList<>();
        JsonStudy.Study jsonee = jsonStudy.new Study();
        jsonee.setCent("的挥洒很疯狂的时间回复哈看");
        jsonee.setSubject("json1");
        studyList.add(jsonee);
        JsonStudy.Study jsonaa = jsonStudy.new Study();
        jsonaa.setCent("xcghjdfghjk");
        jsonaa.setCent("json2");
        studyList.add(jsonaa);
        jsonStudy.setList(studyList);
        String jsonString = JSONObject.toJSONString(jsonStudy);
        CustJson custJson = JSONObject.parseObject(jsonString, CustJson.class);
        System.out.println(custJson.getList());
        System.out.println(JSONObject.toJSONString(jsonStudy));
    }
}
