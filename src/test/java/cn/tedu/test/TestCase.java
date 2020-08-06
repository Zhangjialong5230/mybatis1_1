package cn.tedu.test;

import cn.tedu.config.Config;
import cn.tedu.dao.DemoMapper;
import cn.tedu.dao.UserDao;
import cn.tedu.dao.UserMapper;
import cn.tedu.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestCase {

    AnnotationConfigApplicationContext ctx;

    @Before
    public void init(){
        ctx = new AnnotationConfigApplicationContext(Config.class);
    }

    @After
    public  void  destroy(){
        ctx.close();
    }
    @Test
    public void testDataSource(){
        DataSource ds = ctx.getBean(DataSource.class);
        String sql = "select 'Hello World'";
        try(Connection conn= ds.getConnection()){{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Test
    public void  hello(){
        UserDao dao = ctx.getBean(UserDao.class);
        String str = dao.hello();
        System.out.println(str);
    }


    @Test
    public  void demo(){
        DemoMapper demoMapper = ctx.getBean(DemoMapper.class);
        String str = demoMapper.demo();
        System.out.println(str);
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setUsername("Andy");
        user.setPassword("123");
        user.setAge(10);
        user.setPhone("110");
        user.setEmail("123");
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        int n = userMapper.insertUser(user);
        System.out.println(n);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(3);
        user.setUsername("老余");
        user.setPassword("123456");
        user.setAge(22);
        user.setPhone("22121145");
        user.setEmail("tom@tedu.cn");
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        int n = userMapper.updateUser(user);
        System.out.println(n);

    }


    @Test
    public void testUpdatePassword(){
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        int n = userMapper.updatePassword(3, "abcd");
        System.out.println(n);
    }






    @Test
    public void testDelete(){
        User user = new User();
        user.setId(3);
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        int n = userMapper.Delete(user);
        System.out.println(n);
    }

//    @Test
//    public void testDeleteUser(){
//        UserMapper userMapper = ctx.getBean(UserMapper.class);
//        int n = userMapper.Delete(3 ;
//        System.out.println(n);
//    }

    @Test
    public void testGetAllUsers(){
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        List<User> users = userMapper.getAllUsers();
        users.forEach(user-> System.out.println(user));
    }


    @Test
    public void testGetUserByAge(){
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        List<User> users = userMapper.getUserByAge(11);
        users.forEach(user-> System.out.println(user));
    }

    @Test
    public void testGetUserByUsername(){
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        User user = userMapper.getUserByUsername("Tom");
        System.out.println(user);

    }



}
