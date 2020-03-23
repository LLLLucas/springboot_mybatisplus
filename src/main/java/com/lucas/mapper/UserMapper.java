package com.lucas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucas.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    void selectByMap(HashMap<String, String> hashMap);
}
