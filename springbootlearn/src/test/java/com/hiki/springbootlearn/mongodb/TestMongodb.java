package com.hiki.springbootlearn.mongodb;

import com.hiki.springbootlearn.dao.UserMongoDao;
import com.hiki.springbootlearn.entity.Users;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMongodb {
    @Autowired
    private UserMongoDao umd;

    @Test
    @Ignore
    public void testSave()throws Exception{
        Users user = new Users();
        user.setId(1L);
        user.setName("Hikiy");
        user.setAge(22L);
        user.setPassword("123456");
        umd.saveUser(user);
    }
    @Test
    @Ignore
    public void testFindById()throws Exception{
        Users user = umd.findUserById(1L);
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.getAge());
    }

    @Test
    @Ignore
    public void testUpdate()throws  Exception{
        Users user = new Users();
        user.setId(1L);
        user.setName("newHiki");
        user.setPassword("456789");
        user.setAge(22L);
        long result = umd.updateUser(user);
        if( result == 1 ){
            System.out.println("success!");
        }else{
            System.out.println("fail!");
        }
    }
}
