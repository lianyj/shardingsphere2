package com.example.shardingsphere2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shardingsphere2.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    @Override
    boolean save(User entity);

    List<User> getUserList();
}
