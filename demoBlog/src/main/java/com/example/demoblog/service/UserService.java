package com.example.demoblog.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demoblog.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demoblog.common.Result;
import com.example.demoblog.entity.Users;
import com.example.demoblog.mapper.UserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 注册
    public Result<String> register(Users user) {
        // 检查账号是否重复
        Long count = userMapper.selectCount(
                new LambdaQueryWrapper<Users>()
                        .eq(Users::getUsername, user.getUsername())
        );
        if (count > 0) {
            return Result.error("账号已存在");
        }

        // 密码加密
        user.setPassword(encoder.encode(user.getPassword()));

        // 注册默认状态正常
        user.setStatus(1);

        userMapper.insert(user);
        return Result.success("注册成功");
    }

    // 登录
    public Result<String> login(Users user) {
        Users dbUser = userMapper.selectOne(
                new LambdaQueryWrapper<Users>()
                        .eq(Users::getUsername, user.getUsername())
        );

        if (dbUser == null) {
            return Result.error("账号不存在");
        }

        // 校验密码
        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return Result.error("密码错误");
        }

        // 账号是否被禁用
        if (dbUser.getStatus() == 0) {
            return Result.error("账号已被禁用");
        }



        // 2. 生成 Token
        // 通常存入用户ID或用户名，建议存唯一标识如 ID
        String token = JwtUtil.generateToken(String.valueOf(user.getId()));


        // 生成token
        //String token = UUID.randomUUID().toString().replace("-", "");
        return Result.success(token);
    }
}
