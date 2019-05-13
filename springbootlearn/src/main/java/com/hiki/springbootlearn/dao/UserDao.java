package com.hiki.springbootlearn.dao;

import com.hiki.springbootlearn.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users,Long> {
    Users findByName(String name);
    Users findByNameOrAge(String name, Long age);
}
