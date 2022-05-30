package Bookstore.Control;

import Bookstore.Dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class Test {
    @Autowired
    private Dao dao;
    @RequestMapping(value ="/Test")
    public String test1(){
        System.out.println("测试成功");
//        dao.add();
        return "test";
    }
}
