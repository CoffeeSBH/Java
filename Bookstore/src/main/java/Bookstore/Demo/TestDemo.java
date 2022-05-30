package Bookstore.Demo;

import Bookstore.Dao.Dao;
import Bookstore.Service.ServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
public class TestDemo {
    @Resource
    private Dao dao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    String sql = "insert into bookstore.uesr(name, age) values (?,?)";
//    public void add(){
//        jdbcTemplate.update(sql,"小H","18");
//        System.out.println("成功");
//    }
    public static void main(String[] args) {
        System.out.println("111");
        String sql = "insert into bookstore.uesr(name, age) values (?,?)";
        ApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) app.getBean("jdbcTemplate");
        int A = jdbcTemplate.update(sql,"小A","20");
        System.out.println(A);
//        Dao testDemo = new Dao();
//        testDemo.add();

    }
    @Test
    public void test1() {
        System.out.println("111");
        ApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
        Dao dao = (Dao) app.getBean("dao");
        dao.add();
    }
}
