package com.hao.demospb.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            System.out.println("主机IP："+host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "hello,大家好!";
    }
}
