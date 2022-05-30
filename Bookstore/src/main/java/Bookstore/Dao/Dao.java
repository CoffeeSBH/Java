package Bookstore.Dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Controller
public class Dao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Dao dao;
    String sql = "insert into bookstore.uesr(name, age) values (?,?)";

    @Test
    public void add(){
        System.out.println("666");
        ApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");
        jdbcTemplate.update(sql,"小H","18");
        System.out.println("成功");
//        jdbcTemplate.update(sql,"小H","18");
        dao.add();
    }
}
