package com.example.springboot.util;

import com.example.springboot.control.dto.UserInfoDo;
import com.example.springboot.mapper.entity.UserInfoEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h4>springboot</h4>
 * <p></p>
 *
 * @author : shenbh
 * @date : 2022-05-18 11:52
 **/
@Component
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("====================进入拦截器====================");

        UserInfoEntity user = (UserInfoEntity) request.getSession().getAttribute("user");

        if (user == null){
            response.sendRedirect("/login");
            return  false;
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
