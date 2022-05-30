package com.example.springboot.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h4>springboot</h4>
 * <p></p>
 *
 * @author : shenbh
 * @date : 2022-05-07 16:49
 **/
@Controller
public class TestCon {

    @RequestMapping(value = "/testCon")
    public String testCon(){
        return "test";
    }
}
