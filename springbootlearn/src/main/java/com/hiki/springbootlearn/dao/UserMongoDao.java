package com.hiki.springbootlearn.dao;

import com.hiki.springbootlearn.entity.Users;

public interface UserMongoDao {
    public void saveUser(Users user);
    public Users findUserById(Long id);
    public long updateUser(Users user);
    public void deleteUserById(Long id);
}
