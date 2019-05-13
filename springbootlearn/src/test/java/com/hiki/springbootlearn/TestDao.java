package com.hiki.springbootlearn;

import com.hiki.springbootlearn.entity.Users;
import com.hiki.springbootlearn.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {
    @Autowired
    private UserDao userDao;
        @Test
        public void test() throws Exception {
//            Date date = new Date();
//            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
//            String formattedDate = dateFormat.format(date);
            Users user1 = new Users("aa1", "123456",10L);
            Users user2 = new Users("bb2", "123456", 12L);
            Users user3 = new Users("CC3", "123456", 13L);
            userDao.save(user1);
            userDao.save(user2);
            userDao.save(user3);

            Assert.assertEquals(3, userDao.findAll().size());
            Assert.assertEquals("bb2", userDao.findByNameOrAge("bb3", 12L).getName());
            userDao.delete(userDao.findByName("aa1"));
        }

}
