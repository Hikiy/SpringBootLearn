package com.hiki.springbootlearn.mapper;

import com.hiki.springbootlearn.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "age", column = "age")
    })//如果对象的字段名和数据库的字段名一样，则不需要这个Results注释
    public List<Users> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    public Users getOne(@Param("id") Long id);

    @Insert("INSERT INTO users(name, password, age) VALUES(#{name},#{password}, #{age})")
    public void add(Users user);

    @Update("UPDATE users SET name = #{name},password = #{password}, age = #{age} WHERE id = #{id}")
    public void update(Users user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    public void remove(Long id);
}
