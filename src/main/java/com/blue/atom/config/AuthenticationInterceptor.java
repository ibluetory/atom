package com.blue.atom.config;

import com.blue.atom.annotation.SkipAuth;
import com.blue.atom.entity.User;
import com.blue.atom.service.IUserService;
import com.blue.atom.util.AuthJwtUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token拦截器
 */
@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthJwtUtil authJwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查是否有@SkipAuth注释，有则跳过认证
        if (method.isAnnotationPresent(SkipAuth.class)) {
            SkipAuth skipAuth = method.getAnnotation(SkipAuth.class);
            if (skipAuth.required()) {
                return true;
            }
        }


        // 检查token
        String token = request.getHeader("authorization");
        if (StringUtils.isEmpty(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无token，请重新登录");
            return false;
        }

        // 获取 token 中的 user id
        String userId = authJwtUtil.getUserId(token);
        if (StringUtils.isEmpty(userId)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无token，请重新登录");
            return false;
        }

        // 系统规模小, 可忽略频繁查询数据库带来的影响
        User user = userService.getById(userId);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户不存在，请重新登录");
            return false;
        }

        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
