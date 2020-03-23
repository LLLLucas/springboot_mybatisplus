package com.lucas;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucas.mapper.UserMapper;
import com.lucas.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

    }
    @Test
    public void insert(){
        User user=new User();
        user.setAge(3);
        user.setName("luhang");
        user.setEmail("980631161@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
        System.out.println(user);


    }
    @Test
    public void update(){
        User user=new User();
        user.setId(1241528936986333187L);
        user.setAge(3);
        user.setName("luhang2");
        user.setEmail("980631161@qq.com");
        int insert = userMapper.updateById(user);
    }

    //乐观锁成功
    @Test
    public void testOptimisticLocker(){
        //查询用户
        User user=userMapper.selectById(1L);
        user.setName("卢航");
        userMapper.updateById(user);
    }
    //乐观锁失败
    @Test
    public void testOptimisticLocker2(){

        //模拟线程1
        //查询用户
        User user=userMapper.selectById(1L);
        user.setName("卢航111");


        //模拟线程2
        User user2=userMapper.selectById(1L);
        user2.setName("卢航222");
        userMapper.updateById(user2);

        userMapper.updateById(user);//如果没有乐观锁就会覆盖插队线程的值
    }
    //批量查询
    @Test
    public void testSelect(){
        //User user = userMapper.selectById(1L);//查询单个用户
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);


    }
    //条件查询
    @Test
    public void testSelect2(){
        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("name","luhang");
        List<User> users = userMapper.selectByMap(hashMap);
        users.forEach(System.out::println);

    }

    //分页查询
    @Test
    public void limitSelect(){

        //第一个参数current:当前页
        //第二个参数size:每页个数
        Page<User> objectPage = new Page<>(2,5);
        Page<User> userPage = userMapper.selectPage(objectPage, null);
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);

    }
    //测试删除，通过id删除
    @Test
    public void delete(){
        userMapper.deleteById(1L);
    }

    //测试删除，批量删除
    @Test
    public void deleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(1,2,3,4));
    }

    //测试删除，通过map删除
    @Test
    public void deleteByMap(){
        Map<String ,Object> map=new HashMap<>();
        map.put("name","luhang2");
        int i = userMapper.deleteByMap(map);
    }

}
