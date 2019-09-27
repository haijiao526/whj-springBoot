package com.whj.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: WHJ
 * @create: 2019/9/9
 */
@Controller
@RequestMapping("/base")
public class BaseController {

    @RequestMapping("/study")
    @ResponseBody
    public String base(){
        return "第一个学习程序";
    }
}
