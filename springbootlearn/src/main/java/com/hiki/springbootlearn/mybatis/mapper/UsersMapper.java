package com.hiki.springbootlearn.mybatis.mapper;

import com.hiki.springbootlearn.entity.Users;


import java.util.List;

public interface UsersMapper {
    public List<Users> getAll();

    public Users getOne(Long id);

    public void insert(Users user);

    public void update(Users user);

    public void delete(Long id);
}
