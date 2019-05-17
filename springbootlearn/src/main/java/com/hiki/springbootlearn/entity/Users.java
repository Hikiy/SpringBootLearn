package com.hiki.springbootlearn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity//javax.persistence.Entity;
public class Users implements Serializable {
    private static final long serialVersionUID = 7945207204170209646L;

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private Long age;

    public Users(String name, String password, Long age){
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public Users(){

    }
    @Override
    public String toString(){
        return "User::  id:"+ this.name + " name:" + this.name + "age:" + this.age + " password:" + this.password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
