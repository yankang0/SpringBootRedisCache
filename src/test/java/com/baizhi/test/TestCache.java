package com.baizhi.test;

import com.baizhi.Application;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCache {

    @Autowired
    private UserDAO userDAO;
    /*
    * 查询所有
    * */
    @Test
    public void test1(){
        List<User> users = userDAO.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("+++++++++++++++++++++++");
        List<User> users1 = userDAO.selectAll();
        for (User user : users1) {
            System.out.println(user);
        }


         userDAO.delete("3");


        System.out.println("-------------------------");


        List<User> users2 = userDAO.selectAll();
        for (User user : users2) {
            System.out.println(user);
        }

    }
    /*
    * 通过ID查询
    * */
    @Test
    public void test2(){
        User user = userDAO.selectByID("1");
        System.out.println(user);
        System.out.println("++++++++++++++++");
        User user2 = userDAO.selectByID("1");
        System.out.println(user2);
        System.out.println("-------------------");
        User user3 = userDAO.selectByID("1");
        System.out.println(user3);
    }
}
