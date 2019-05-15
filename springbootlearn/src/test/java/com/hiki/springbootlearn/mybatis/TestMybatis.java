package com.hiki.springbootlearn.mybatis;

import com.hiki.springbootlearn.entity.Users;
import com.hiki.springbootlearn.mapper.UserMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatis {
    @Autowired
    private UserMapper userMapper;

    @Test
    //@Ignore
    public void testMybatis1(){
        List<Users> users = userMapper.getAll();
        for (Users user:users) {
            System.out.println(user.getName());
            System.out.println(user.getAge());
            System.out.println(user.getId());
            System.out.println(user.getPassword());
        }
    }

    @Test
    @Ignore
    public void testGetOne(){
        Users user = userMapper.getOne(14L);
        System.out.println(user.getName());
    }

    @Test
    @Ignore
    public void testAdd(){
        Users user = new Users();
        user.setAge(22L);
        user.setName("hiki");
        user.setPassword("123456");
        userMapper.add(user);
    }

    @Test
    @Ignore
    public void testUpdate(){
        Users user = new Users();
        user.setName("hikiy");
        user.setId(16L);
        user.setPassword("456789");
        user.setAge(22L);
        userMapper.update(user);
    }

    @Test
    @Ignore
    public void testRemove(){
        userMapper.remove(16L);
    }
}
