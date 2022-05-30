package com.example.springboot.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <h4>springboot</h4>
 * <p></p>
 *
 * @author : shenbh
 * @date : 2022-05-24 09:49
 **/
@Controller
public class UserActionCon {

    @Resource
    protected HttpServletRequest request;

    @RequestMapping("/user_exit")
    public String exit() {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/login")
    public String login1() {
        if(request.getSession().getAttribute("user")!=null){
            return "/index";
        };
        return "login";
    }
}
