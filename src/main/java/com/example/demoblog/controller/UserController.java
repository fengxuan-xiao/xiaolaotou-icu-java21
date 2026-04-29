package com.example.demoblog.controller;

import com.example.demoblog.common.Result;
import com.example.demoblog.entity.Users;
import com.example.demoblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:5173")  // 允许前端跨域请求，端口为Vue默认开发端口
public class UserController {

    @Resource
    private UserService userService;

    //Result<T>统一使用Result<T>封装数据，统一返回JSON数据
    @PostMapping("/register")
    public Result<String> register(@RequestBody Users user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody Users user) {
        return userService.login(user);
    }
}
