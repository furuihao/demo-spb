package com.hao.demospb.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hao
 * @create 2018/8/11
 */
@RestController
public class LoginController {
    @RequestMapping("/login")
    public Map<String, Object> login(HttpServletRequest request) {
        request.getSession().setAttribute("username", "admin");
        request.getSession().setAttribute("sessionId", request.getSession().getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sessionId", request.getSession().getId());
        return map;
    }

    @RequestMapping(value = "/getSession")
    public String get(HttpServletRequest request) {
        String sessionId = (String) request.getSession().getAttribute("sessionId");
        String host = null;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
//        System.out.println("主机IP："+host);

        return "主机IP："+host+",sessionId:"+sessionId;
    }
}
