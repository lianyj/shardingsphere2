package com.example.shardingsphere2.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shardingsphere2.entity.User;


@DS("ds-sharding")
public interface UserMapper extends BaseMapper<User> {
    
}
