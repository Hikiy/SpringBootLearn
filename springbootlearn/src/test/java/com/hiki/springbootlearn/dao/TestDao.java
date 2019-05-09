package com.hiki.springbootlearn.dao;

import com.hiki.springbootlearn.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class TestDao {
    @Autowired
    private UserDao userDao;
        @Test
        public void test() throws Exception {
            Date date = new Date();
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
            String formattedDate = dateFormat.format(date);
            User user1 = new User("aa1", "123456",10L);
            User user2 = new User("bb2", "123456", 12L);
            User user3 = new User("CC3", "123456", 13L);
            userDao.save(user1);
            userDao.save(user2);
            userDao.save(user3);

            Assert.assertEquals(3, userDao.findAll().size());
            Assert.assertEquals("bb2", userDao.findByNameOrAge("bb", 12L).getName());
            userDao.delete(userDao.findByName("aa1"));
        }

}
