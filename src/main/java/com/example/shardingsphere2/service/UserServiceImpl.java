package com.example.shardingsphere2.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingsphere2.entity.User;
import com.example.shardingsphere2.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@DS("ds-sharding")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean save(User entity){
        return super.save(entity);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(Wrappers.lambdaQuery());
    }
}
