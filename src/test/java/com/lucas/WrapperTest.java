package com.lucas;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucas.mapper.UserMapper;
import com.lucas.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void loads(){
        //查询name不为空，并且邮箱不为空，年龄大于12
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();

        queryWrapper.isNotNull("name").isNotNull("email").ge("age",12);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void loads2(){
        //查询name等于卢航
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name","卢航222");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void loads3(){
        //查询年龄在25-30,且只查询姓名和age
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();

        queryWrapper.between("age",25,30);
        queryWrapper.select("name","age");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    @Test
    void loads4(){
        //set
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

}
