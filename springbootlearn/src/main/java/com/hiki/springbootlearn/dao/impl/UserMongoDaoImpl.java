package com.hiki.springbootlearn.dao.impl;

import com.hiki.springbootlearn.dao.UserMongoDao;
import com.hiki.springbootlearn.entity.Users;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserMongoDaoImpl implements UserMongoDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(Users user) {
        mongoTemplate.save(user);
    }

    @Override
    public Users findUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        Users user =  mongoTemplate.findOne(query , Users.class);
        return user;
    }

    @Override
    public long updateUser(Users user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("name", user.getName()).set("password", user.getPassword()).set("age",user.getAge());
        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,Users.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return result.getMatchedCount();
        else
            return 0;
    }

    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Users.class);
    }
}
