package com.hao.demospb.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author hao
 * @create 2018/1/14
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/say")
    public String hello(){
        return "hello,大家好!";
    }
}
