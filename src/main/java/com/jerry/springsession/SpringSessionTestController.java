package com.jerry.springsession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 已经在本地的nginx上配置了反向代理，测试时请启动两个实例，
 * 分别用 8081 和 8082端口，nginx会将请求转发到这两个端口上。
 */
@RestController
public class SpringSessionTestController {
    @Value("${server.port}")
    Integer  port;

    @GetMapping("/setspringsession")
    public String set(HttpSession session) {
        session.setAttribute("user", "jerry");
        return String.valueOf(port);
    }

    @GetMapping("/getspringsession")
    public String get(HttpSession session) {
        return session.getAttribute("user") + ":" + port;
    }

    @PostMapping("/list")
    public List<Long> list(@RequestBody List<Long> list){
        return list;
    }
}
