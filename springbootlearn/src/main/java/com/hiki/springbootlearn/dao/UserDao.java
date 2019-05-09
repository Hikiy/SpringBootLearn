package com.hiki.springbootlearn.dao;

import com.hiki.springbootlearn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByName(String name);
    User findByNameOrAge(String name,Long age);
}
