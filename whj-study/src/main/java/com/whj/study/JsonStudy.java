package com.whj.study;

import java.util.List;

/**
 * @description:
 * @author: WHJ
 * @create: 2019/9/14
 */
public class JsonStudy {

    private List<Study> list;
    private String jsonStr;
    private String name;

    public List<Study> getList() {
        return list;
    }

    public void setList(List<Study> list) {
        this.list = list;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    class Study{
        private String subject;
        private String cent;

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getCent() {
            return cent;
        }

        public void setCent(String cent) {
            this.cent = cent;
        }

        public Study() {
        }
    }
}
