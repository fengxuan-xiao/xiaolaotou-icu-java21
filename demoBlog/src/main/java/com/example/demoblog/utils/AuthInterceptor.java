package com.example.demoblog.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取 Token
        String token = request.getHeader("Authorization");

        // 2. 判断 Token 是否存在且格式正确 (Bearer <token>)
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"未登录或Token无效\"}");
            return false; // 拦截请求
        }

        // 3. 验证 Token 有效性 (这里假设你有一个 JwtUtil 工具类)
        String realToken = token.substring(7);
        try {
            // URL 解码（处理前端传输时的 URL 编码问题）
            realToken = URLDecoder.decode(realToken, StandardCharsets.UTF_8);
            // 如果解析失败或过期，会抛出异常
            JwtUtil.verifyToken(realToken);
            // 可选：将用户信息存入 request，供 Controller 使用
            // request.setAttribute("userId", JwtUtil.getUserId(realToken));
            return true; // 放行
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"Token已过期或无效\"}");
            return false;
        }
    }
}
